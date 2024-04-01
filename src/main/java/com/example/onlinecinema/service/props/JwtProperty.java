package com.example.onlinecinema.service.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperty {

    private String secret;
    private Long access;
    private Long refresh;
}

