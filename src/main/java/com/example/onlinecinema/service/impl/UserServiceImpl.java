package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.user.Role;
import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.repository.UserRepository;
import com.example.onlinecinema.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Caching(
            cacheable = {
                @Cacheable(value = "UserService::getById", key = "#user.id"),
                @Cacheable(value = "UserService::getUserByUsername", key = "#user.username")
            }
    )
    public UserEntity create(UserEntity user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().isEmpty()) {
            user.setRoles(Set.of(Role.ROLE_USER));
        }
        return userRepository.save(user);
    }

    @Override
    @Caching(
    put = {
            @CachePut(value = "UserService::getById", key = "#user.id"),
            @CachePut(value = "UserService::getUserByUsername", key = "#user.username")
    }
    )
    public UserEntity update(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "UserService::getUserById", key = "#id")
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User by id not found exception"));
    }

    @Override
    @Cacheable(value = "UserService::getUserByUsername", key = "#username")
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User by username not found exception"));
    }

    @Override
    @CacheEvict(value = "UserService::deleteUserById", key = "#id")
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
