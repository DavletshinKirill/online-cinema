package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.movie.MovieImage;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.repository.MovieRepository;
import com.example.onlinecinema.service.interfaces.MinioService;
import com.example.onlinecinema.service.interfaces.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    @Override
    public void uploadImage(Long id, MovieImage movieImage) {
        Movie movie = getMovieById(id);
        String filename = minioService.upload(movieImage);
        movie.setPoster(filename);
        movieRepository.save(movie);
    }

    private final MovieRepository movieRepository;
    private final MinioService minioService;

    @Override
    public Movie create(Movie movie) {
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()) {
            throw new IllegalStateException("Movie already exist");
        }
        return movieRepository.save(movie);
    }

    @Override
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
    public void deleteById(Long id) {
        if(movieRepository.findById(id).isEmpty())
            throw new IllegalStateException("Movie already delete");
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

    @Override
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
}
