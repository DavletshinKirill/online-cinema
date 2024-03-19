package com.example.onlinecinema.web.DTO.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema($schema = "Request for login")
public class JwtRequest {

    @Schema(description = "username", example = "kirill@gmail.com")
    @NotNull(message = "Username must be not null")
    private String username;

    @Schema(description = "password", example = "123456")
    @NotNull(message = "Username must be not null")
    private String password;
}
