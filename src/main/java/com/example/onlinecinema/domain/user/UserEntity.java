package com.example.onlinecinema.domain.user;

import com.example.onlinecinema.domain.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

public class UserEntity extends BaseEntity {
    @Column(name = "username", unique = true)
    private String username;
    private String password;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;
}
