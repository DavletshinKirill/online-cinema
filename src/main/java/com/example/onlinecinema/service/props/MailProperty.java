package com.example.onlinecinema.service.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperty {
    private String host;
    private int port;
    private String username;
    private String password;
}
