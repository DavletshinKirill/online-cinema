package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.repository.MovieRepository;
import com.example.onlinecinema.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie create(Movie movie) {
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
            throw new IllegalStateException("User already exist");
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie by id not found exception"));
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieRepository.findAll());
    }

}
