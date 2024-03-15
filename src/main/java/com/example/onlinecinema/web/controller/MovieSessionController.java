package com.example.onlinecinema.web.controller;

import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.service.interfaces.MovieSessionService;
import com.example.onlinecinema.web.DTO.MovieSessionDTO;
import com.example.onlinecinema.web.mapper.MovieSessionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movieSession")
@RequiredArgsConstructor
@Validated
@Tag(name = "Movie Session Controller", description = "Movie Session API")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    @Operation(summary = "Не работает, переделай")
    @PutMapping("update")
    public MovieSessionDTO update(@RequestBody MovieSessionDTO movieSessionDTO) {
        MovieSession movieSession = movieSessionMapper.toEntity(movieSessionDTO);
        MovieSession updatedMovieSession = movieSessionService.update(movieSession);
        return movieSessionMapper.toDTO(updatedMovieSession);
    }

    @Operation(summary = "get movie session by id")
    @GetMapping("/get/{id}")
    public MovieSessionDTO getById(@PathVariable Long id) {
        MovieSession movieSession = movieSessionService.getMovieSessionById(id);
        return movieSessionMapper.toDTO(movieSession);
    }

    @Operation(summary = "get all sessions by movie Id")
    @GetMapping("/get/movie/{id}")
    public List<MovieSessionDTO> getAllSessionByMovieId(@PathVariable Long id) {
        List<MovieSession> movieSessions = movieSessionService.getAllMovieSessionsByMovieId(id);
        return movieSessionMapper.toDTO(movieSessions);
    }

    @Operation(summary = "delete movie session by id")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        movieSessionService.deleteById(id);
    }


}
