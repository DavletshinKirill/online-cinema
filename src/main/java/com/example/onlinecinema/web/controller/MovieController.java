package com.example.onlinecinema.web.controller;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.movie.MovieImage;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.service.interfaces.MinioService;
import com.example.onlinecinema.service.interfaces.MovieService;
import com.example.onlinecinema.web.DTO.MovieDTO;
import com.example.onlinecinema.web.DTO.MovieImageDTO;
import com.example.onlinecinema.web.DTO.MovieSessionDTO;
import com.example.onlinecinema.web.mapper.ImageMapper;
import com.example.onlinecinema.web.mapper.MovieMapper;
import com.example.onlinecinema.web.mapper.MovieSessionMapper;
import com.example.onlinecinema.web.validation.OnCreate;
import com.example.onlinecinema.web.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
@Validated
@Tag(name = "Movie Controller", description = "Movie API")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final MovieSessionMapper movieSessionMapper;
    private final ImageMapper imageMapper;
    private final MinioService minioService;

    @Operation(summary = "create movie")
    @PostMapping("/create")
    public MovieDTO createMovie(@Validated(OnCreate.class) @RequestBody MovieDTO movieDTO) {
        Movie movie = movieMapper.toEntity(movieDTO);
        Movie createdMovie = movieService.create(movie);
        return movieMapper.toDTO(createdMovie);
    }
    @Operation(summary = "update movie")
    @PutMapping("/update")
    public MovieDTO updateMovie(@Validated(OnUpdate.class) @RequestBody MovieDTO movieDTO) {
        Movie movie = movieMapper.toEntity(movieDTO);
        Movie updatedMovie = movieService.update(movie);
        return movieMapper.toDTO(updatedMovie);
    }
    @Operation(summary = "delete movie")
    @DeleteMapping("delete/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
    }
    @Operation(summary = "get movie by id")
    @GetMapping("get/{id}")
    public MovieDTO getMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        return movieMapper.toDTO(movie);
    }

    @Operation(summary = "get all movies")
    @GetMapping("get/all")
    public List<MovieDTO> getMovies() {
        List<Movie> movieList = movieService.getAllMovies();
        return movieMapper.toDTO(movieList);
    }

    @Operation(summary = "create movie sessions")
    @PostMapping("/create/movieSession/{id}")
    public MovieSessionDTO createMovieSession(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody MovieSessionDTO movieSessionDTO) {
        MovieSession movieSession = movieSessionMapper.toEntity(movieSessionDTO);
        movieService.createMovieSession(id, movieSession);
        return movieSessionDTO;
    }

    @Operation(summary = "upload image to movie")
    @PostMapping("/{id}/image")
    public void uploadImage(@PathVariable Long id, @Validated @ModelAttribute MovieImageDTO taskImageDto) {
        MovieImage image = imageMapper.toEntity(taskImageDto);
        movieService.uploadImage(id, image);
    }

    @SneakyThrows
    @GetMapping(path = "/download/{filename}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("filename") String file) {
        byte[] data = minioService.download(file).readAllBytes();
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
                .body(resource);
    }
}
