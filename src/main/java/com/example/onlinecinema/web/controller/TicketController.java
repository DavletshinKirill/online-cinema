package com.example.onlinecinema.web.controller;

import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.service.interfaces.TicketService;
import com.example.onlinecinema.web.DTO.TicketDTO;
import com.example.onlinecinema.web.mapper.TicketMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
@Validated
@Tag(name = "Ticket Controller", description = "Ticket API")
public class TicketController {
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @Operation(summary = "Book ticket")
    @PutMapping("/book")
    public List<TicketDTO> bookTicket(@RequestBody ArrayList<Long> indexes) throws MessagingException {
        ArrayList<Ticket> tickets = ticketService.bookTicket(indexes);
        return ticketMapper.toDTO(tickets);
    }

    @Operation(summary = "Buy ticket")
    @PutMapping("/buy")
    public List<TicketDTO> buyTicket(@RequestBody ArrayList<Long> indexes) {
        ArrayList<Ticket> tickets = ticketService.buyTicket(indexes);
        return ticketMapper.toDTO(tickets);
    }

    @Operation(summary = "Get tickets by session id")
    @GetMapping("/getBySessionId/{id}")
    public List<TicketDTO> buyTicket(@PathVariable("id") Long session_id) {
        ArrayList<Ticket> tickets = ticketService.getAllTicketsBySessionId(session_id);
        return ticketMapper.toDTO(tickets);
    }
}
