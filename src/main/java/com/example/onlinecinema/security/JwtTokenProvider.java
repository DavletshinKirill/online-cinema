package com.example.onlinecinema.security;


import com.example.onlinecinema.domain.user.Role;
import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.service.interfaces.UserService;
import com.example.onlinecinema.service.props.JwtProperty;
import com.example.onlinecinema.web.DTO.auth.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperty jwrProperty;

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwrProperty.getSecret().getBytes());
    }

    public String createAccessToken(Long user_id, String username, Set<Role> roles) {
        Claims claims = Jwts.claims()
                .subject(username)
                .add("id", user_id)
                .add("roles", resolveRoles(roles))
                .build();

        Instant validity = Instant.now()
                .plus(jwrProperty.getAccess(), ChronoUnit.HOURS);
        return Jwts.builder()
                .claims(claims)
                .expiration(Date.from(validity))
                .signWith(key)
                .compact();

    }

    private List<String> resolveRoles(Set<Role> roles) {
        return roles.stream().map(Enum::name).collect(Collectors.toList());
    }

    public String createRefreshToken(Long user_id, String username) {
        Claims claims = Jwts.claims()
                .subject(username)
                .add("id", user_id)
                .build();
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwrProperty.getRefresh());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }


    public JwtResponse refreshUserTokens(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();
        if (!validateToken(refreshToken)) {
            throw new AccessDeniedException("Token is not valid");
        }
        Long userId = Long.valueOf(getId(refreshToken));
        UserEntity user = userService.getUserById(userId);
        jwtResponse.setId(userId);
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(createAccessToken(userId,
                user.getUsername(), user.getRoles()));
        jwtResponse.setFreshToken(createRefreshToken(userId, user.getUsername()));
        return jwtResponse;
    }


    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }


    private String getId(String token) {
        return Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }

    public Authentication getAuthentication(String token) {
        String userName = getUserName(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserName(String token) {
        return Jwts
                .parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}