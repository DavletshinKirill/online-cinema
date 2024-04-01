package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.repository.MovieSessionRepository;
import com.example.onlinecinema.service.interfaces.MovieService;
import com.example.onlinecinema.service.interfaces.MovieSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final MovieService movieService;


    @Override
    @CachePut(value = "MovieSessionService::update", key = "#movieSession.id")
    public MovieSession update(MovieSession movieSession) {

        MovieSession movieValidated = getMovieSessionById(movieSession.getId());
        movieValidated.setPrice(movieSession.getPrice());
        movieValidated.setDate_start(movieSession.getDate_start());

        return movieSessionRepository.save(movieValidated);
    }

    @Override
    @CacheEvict(value = "MovieSessionService::deleteById", key = "#id")
    public void deleteById(Long id) {
        movieSessionRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "MovieSessionService::getMovieSessionById", key = "#id")
    public MovieSession getMovieSessionById(Long id) {
        return movieSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie Session by id not found exception"));
    }

    @Override
    public List<MovieSession> getAllMovieSessionsByMovieId(Long id) {
        return movieSessionRepository.findAllByMovieId(id);
    }

    @Override
    public Movie getMovieByMovieSessionId(Long movieSessionId) {
        Long movie_id = movieSessionRepository.findMovieIdByMovieSessionId(movieSessionId);
        return movieService.getMovieById(movie_id);
    }

    @Override
    public void deletePastSession() {
        List<MovieSession> sessions = movieSessionRepository.findPastSession(LocalDateTime.now());
        movieSessionRepository.deleteAll(sessions);
    }
}
