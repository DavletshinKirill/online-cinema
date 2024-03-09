package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class MovieSessionDTO {

    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm")
    private LocalDateTime date_start;


    private CinemaHallDTO cinemaHallDTO;
}
