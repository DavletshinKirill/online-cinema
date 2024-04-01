package com.example.onlinecinema.web.DTO;

import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.web.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Schema(description = "Ticket DTO")
public class TicketDTO {

    @Schema(name = "id", example = "1")
    @NotNull(message = "Id must be not null", groups = {OnUpdate.class})
    private Long id;

    @Schema(name = "Status booking")
    private Status booking;
}
