package com.example.onlinecinema.config;

import com.example.onlinecinema.web.mapper.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CinemaHallMapper cinemaHallMapper() {
        return Mappers.getMapper(CinemaHallMapper.class);
    }

    @Bean
    public MovieMapper movieMapper() {
        return Mappers.getMapper(MovieMapper.class);
    }

    @Bean
    public MovieSessionMapper movieSessionMapper() {
        return Mappers.getMapper(MovieSessionMapper.class);
    }

    @Bean
    public TicketMapper ticketMapper() {
        return Mappers.getMapper(TicketMapper.class);
    }

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }


}
