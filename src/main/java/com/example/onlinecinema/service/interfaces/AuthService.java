package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.web.DTO.auth.JwtRequest;
import com.example.onlinecinema.web.DTO.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);
}
