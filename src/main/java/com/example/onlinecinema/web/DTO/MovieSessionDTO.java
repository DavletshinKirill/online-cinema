package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.web.validation.OnCreate;
import com.example.onlinecinema.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Schema(description = "MovieSession DTO")
public class MovieSessionDTO {

    @Schema(name = "id", example = "3")
    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm")
    private LocalDateTime date_start;

    @Schema(name = "rowsAmount", example = "5")
    @NotNull(message = "rowsAmount must be not null", groups = {OnUpdate.class, OnCreate.class})
    private int rowsAmount;

    @Schema(name = "seatsInRow", example = "5")
    @NotNull(message = "seatsInRow must be not null", groups = {OnUpdate.class, OnCreate.class})
    private int seatsInRow;


    @Schema(name = "format", example = "2D")
    @NotNull(message = "format must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String format;


    @Schema(name = "price", example = "500")
    @NotNull(message = "Price must be not null", groups = {OnUpdate.class, OnCreate.class})
    private Float price;
}
