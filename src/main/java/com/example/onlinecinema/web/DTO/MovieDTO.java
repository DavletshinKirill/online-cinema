package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.web.validation.OnCreate;
import com.example.onlinecinema.web.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
public class MovieDTO{

    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @NotNull(message = "Title must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Title length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @NotNull(message = "Director must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Director length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String director;

    @NotNull(message = "Poster must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Poster length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String poster;

    @NotNull(message = "DurationMinutes must be not null",  groups = {OnCreate.class, OnUpdate.class})
    private int durationMinutes;

    private List<MovieSession> movieSessions;
}
