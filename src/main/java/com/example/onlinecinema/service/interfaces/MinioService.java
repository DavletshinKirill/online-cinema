package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.movie.MovieImage;

import java.io.InputStream;

public interface MinioService {
    String upload(MovieImage movieImage);
    InputStream download(String moviePath);
}
