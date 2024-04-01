package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;

import java.util.List;

public interface MovieSessionService {

    MovieSession update(MovieSession movieSessionDTO);


    void deleteById(Long id);
    MovieSession getMovieSessionById(Long id);

    List<MovieSession> getAllMovieSessionsByMovieId(Long id);

    Movie getMovieByMovieSessionId(Long movieSessionId);

    void deletePastSession();
}
