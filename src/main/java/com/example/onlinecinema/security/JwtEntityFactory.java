package com.example.onlinecinema.security;

import com.example.onlinecinema.domain.user.Role;
import com.example.onlinecinema.domain.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity create(UserEntity user) {
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static Collection<GrantedAuthority> mapToGrantedAuthorities(ArrayList<Role> roles) {
        return roles.stream().map(Enum::name).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


}
