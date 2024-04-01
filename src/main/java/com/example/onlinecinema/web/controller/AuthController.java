package com.example.onlinecinema.web.controller;

import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.service.interfaces.AuthService;
import com.example.onlinecinema.service.interfaces.MailService;
import com.example.onlinecinema.service.interfaces.UserService;
import com.example.onlinecinema.web.DTO.UserDTO;
import com.example.onlinecinema.web.DTO.auth.JwtRequest;
import com.example.onlinecinema.web.DTO.auth.JwtResponse;
import com.example.onlinecinema.web.mapper.UserMapper;
import com.example.onlinecinema.web.validation.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final MailService mailService;
    private final UserMapper userMapper;

    @Operation(summary = "login")
    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Operation(summary = "register")
    @PostMapping("/register")
    public UserDTO register(@Validated(OnCreate.class) @RequestBody UserDTO userDto) throws MessagingException {
        UserEntity user = userMapper.toEntity(userDto);
        UserEntity createdUser = userService.create(user);
        mailService.sendRegistrationEmail(user,  new Properties());
        return userMapper.toDTO(createdUser);
    }

    @Operation(summary = "refresh token")
    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
