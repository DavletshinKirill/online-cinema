package com.example.onlinecinema.web.mapper;

import com.example.onlinecinema.domain.session.CinemaHall;
import com.example.onlinecinema.web.DTO.CinemaHallDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CinemaHallMapper extends Mappable<CinemaHall, CinemaHallDTO> {
}
