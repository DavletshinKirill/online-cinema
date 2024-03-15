package com.example.onlinecinema.config;

import com.example.onlinecinema.service.props.MinioProperty;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    private final MinioProperty minioProperty;
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProperty.getUrl())
                .credentials(minioProperty.getAccessKey(), minioProperty.getSecretKey())
                .build();
    }
}
