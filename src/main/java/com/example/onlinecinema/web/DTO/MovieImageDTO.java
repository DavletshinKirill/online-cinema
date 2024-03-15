package com.example.onlinecinema.web.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieImageDTO {
    @NotNull(message = "Image must be not null.")
    private MultipartFile file;
}
