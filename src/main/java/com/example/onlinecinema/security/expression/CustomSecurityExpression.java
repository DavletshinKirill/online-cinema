package com.example.onlinecinema.security.expression;


import com.example.onlinecinema.domain.user.Role;
import com.example.onlinecinema.security.JwtEntity;
import com.example.onlinecinema.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("customSecurityExpression")
@RequiredArgsConstructor
public class CustomSecurityExpression {
    private final UserService userService;

    public boolean canAccessUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();
        return userId.equals(id) || hasAnyRole(authentication, Role.ROLE_ADMIN);
    }

    private boolean hasAnyRole(Authentication authentication, Role... roles) {
        for(Role role: roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            if(authentication.getAuthorities().contains(authority)) {
                return true;
            }
        }
        return false;
    }

}
