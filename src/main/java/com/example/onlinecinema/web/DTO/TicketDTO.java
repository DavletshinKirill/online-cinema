package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.web.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TicketDTO {
    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    private Status booking;
}
