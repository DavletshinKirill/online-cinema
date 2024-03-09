package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.web.validation.OnCreate;
import com.example.onlinecinema.web.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;


@Data
@AllArgsConstructor
public class CinemaHallDTO {

    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @NotNull(message = "AmountRows must be not null", groups = {OnUpdate.class, OnCreate.class})
    private int amountRows;

    @NotNull(message = "AmountRows must be not null", groups = {OnUpdate.class, OnCreate.class})
    private int seatsInRow;

    private ArrayList<Ticket> tickets;
}
