package com.example.onlinecinema.web.mapper;

import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.web.DTO.MovieSessionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MovieSessionMapper extends Mappable<MovieSession, MovieSessionDTO> {
}
