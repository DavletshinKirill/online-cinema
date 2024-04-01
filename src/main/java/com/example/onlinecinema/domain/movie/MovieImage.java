package com.example.onlinecinema.domain.movie;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class MovieImage {
    private MultipartFile file;
}
