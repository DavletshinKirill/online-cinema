package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.web.validation.OnCreate;
import com.example.onlinecinema.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Movie DTO")
public class MovieDTO{

    @Schema(name = "id", example = "3")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @Schema(name = "title", example = "Довод")
    @NotNull(message = "Title must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Title length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Schema(name = "director", example = "Кристофер Нолан")
    @NotNull(message = "Director must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Director length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String director;

    @Schema(name = "poster", example = "Тут будет ваша картинка")
    @NotNull(message = "Poster must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Poster length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String poster;

    @Schema(name = "country", example = "США, Китай, Россия")
    @NotNull(message = "Сountry must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Сountry length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String country;

    @Schema(name = "actors", example = "Брэд Питт")
    @NotNull(message = "Сountry must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Сountry length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String actors;

    @Schema(name = "genre", example = "Драма, военный")
    @NotNull(message = "Genre must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Genre length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String genre;

    @Schema(name = "description", example = "Стоит апрель 1945 года, и победа над немцами уже близка: союзники уверенно продвигаются вглубь вражеской территории, и оказываемое им сопротивление становится слабее день ото дня.")
    @NotNull(message = "Description must be not null",  groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Description length must be smaller that 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @Schema(name = "year", example = "2014")
    @NotNull(message = "Year must be not null",  groups = {OnCreate.class, OnUpdate.class})
    private int year;

    @Schema(name = "durationMinutes", example = "150")
    @NotNull(message = "DurationMinutes must be not null",  groups = {OnCreate.class, OnUpdate.class})
    private int durationMinutes;

    @Schema(name = "movieSessions", example = "[]")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<MovieSessionDTO> movieSessions;
}
