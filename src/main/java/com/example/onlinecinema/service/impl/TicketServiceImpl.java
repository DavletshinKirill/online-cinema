package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.repository.TicketRepository;
import com.example.onlinecinema.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    @Override
    public Ticket bookTicket(int index) {
        Ticket ticket = ticketRepository.findById((long) index).get();
        ticket.setBooking(Status.BOOKED);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket buyTicket(int index) {
        Ticket ticket = ticketRepository.findById((long) index).get();
        ticket.setBooking(Status.BOUGHT);
        return ticketRepository.save(ticket);
    }
}
