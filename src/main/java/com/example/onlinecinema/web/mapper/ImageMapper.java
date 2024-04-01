package com.example.onlinecinema.web.mapper;

import com.example.onlinecinema.domain.movie.MovieImage;
import com.example.onlinecinema.web.DTO.MovieImageDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper extends Mappable<MovieImage, MovieImageDTO> {
}
