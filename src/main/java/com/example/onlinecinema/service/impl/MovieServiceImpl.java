package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.movie.MovieImage;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.repository.MovieRepository;
import com.example.onlinecinema.service.interfaces.MinioService;
import com.example.onlinecinema.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {


    private final MovieRepository movieRepository;
    private final MinioService minioService;

    @Override
    @Transactional
    @Cacheable(value = "MovieService::getMovieById", key = "#movie.id")
    public Movie create(Movie movie) {
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
            throw new IllegalStateException("Movie already exist");
        }
        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    @CachePut(value = "MovieService::getMovieById", key = "#movie.id")
    public Movie update(Movie movie) {

        Movie validatedMovie = movieRepository.findById(movie.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie by id not found exception(update movie method)"));
        validatedMovie.setTitle(movie.getTitle());
        validatedMovie.setDirector(movie.getDirector());
        validatedMovie.setPoster(movie.getPoster());
        validatedMovie.setDurationMinutes(movie.getDurationMinutes());
        return movieRepository.save(validatedMovie);
    }

    @Override
    @Transactional
    @CacheEvict(value = "MovieService:deleteById", key = "#id")
    public void deleteById(Long id) {
        if(movieRepository.findById(id).isEmpty())
            throw new IllegalStateException("Movie already delete");
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "MovieService::getMovieById", key = "#id")
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie by id not found exception"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieRepository.findAll());
    }

    @Override
    @Transactional
    public MovieSession createMovieSession(Long id, MovieSession movieSession) {

        movieSession.createTickets();

        Movie movie = getMovieById(id);

        if(movie.getMovieSessions().isEmpty()) {
            movie.setMovieSessions(new ArrayList<>());
        }

        movie.getMovieSessions().add(movieSession);
        movieRepository.save(movie);
        return movieSession;
    }

    @Override
    @Transactional
    public void uploadImage(Long id, MovieImage movieImage) {
        Movie movie = getMovieById(id);
        String filename = minioService.upload(movieImage);
        movie.setPoster(filename);
        movieRepository.save(movie);
    }
}
