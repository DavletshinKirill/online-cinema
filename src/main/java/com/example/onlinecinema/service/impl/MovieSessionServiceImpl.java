package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.repository.MovieSessionRepository;
import com.example.onlinecinema.service.interfaces.MovieSessionService;
import com.example.onlinecinema.web.DTO.MovieSessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;

    @Override
    public MovieSession create(MovieSession movieSession) {
        return movieSessionRepository.save(movieSession);
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        return movieSessionRepository.save(movieSession);
    }

    @Override
    public void deleteById(Long id) {
        movieSessionRepository.deleteById(id);
    }

    @Override
    public MovieSession getMovieSessionById(Long id) {
        return movieSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie Session by id not found exception"));
    }

    @Override
    public List<MovieSessionDTO> getAllByMovieId(Long id) {
        return new ArrayList<>();
    }
}
