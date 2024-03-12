package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.web.DTO.MovieSessionDTO;

import java.util.List;

public interface MovieSessionService {
    MovieSession create(MovieSession movieSessionDTO);
    MovieSession update(MovieSession movieSessionDTO);
    void deleteById(Long id);
    MovieSession getMovieSessionById(Long id);

    List<MovieSessionDTO> getAllByMovieId(Long id);
}
