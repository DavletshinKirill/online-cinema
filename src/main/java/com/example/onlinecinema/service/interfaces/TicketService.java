package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.ticket.Ticket;

public interface TicketService {
    Ticket bookTicket(int index);
    Ticket buyTicket(int index);
}
