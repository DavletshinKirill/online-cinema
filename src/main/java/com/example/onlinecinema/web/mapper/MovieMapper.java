package com.example.onlinecinema.web.mapper;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.web.DTO.MovieDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper extends Mappable<Movie, MovieDTO> {
}
