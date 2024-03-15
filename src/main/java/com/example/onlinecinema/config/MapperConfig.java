package com.example.onlinecinema.config;

import com.example.onlinecinema.web.mapper.MovieMapper;
import com.example.onlinecinema.web.mapper.MovieSessionMapper;
import com.example.onlinecinema.web.mapper.TicketMapper;
import com.example.onlinecinema.web.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

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
