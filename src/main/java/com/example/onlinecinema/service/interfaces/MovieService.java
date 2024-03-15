package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;

import java.util.List;

public interface MovieService {

    Movie create(Movie movie);
    Movie update(Movie movie);
    void deleteById(Long id);
    Movie getMovieById(Long id);

    List<Movie> getAllMovies();

    MovieSession createMovieSession(Long id, MovieSession movieSession);
}
