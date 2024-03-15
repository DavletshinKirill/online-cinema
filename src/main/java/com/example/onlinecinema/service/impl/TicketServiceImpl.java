package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.repository.TicketRepository;
import com.example.onlinecinema.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    @Override
    public ArrayList<Ticket> bookTicket(ArrayList<Long> indexes) {

        ArrayList<Ticket> tickets = new ArrayList<>(ticketRepository.findAllById(indexes));
        tickets.forEach(ticket -> ticket.setBooking(Status.BOOKED));
        ticketRepository.saveAll(tickets);
        return tickets;
    }

    @Override
    public ArrayList<Ticket> buyTicket(ArrayList<Long> indexes) {
        ArrayList<Ticket> tickets = new ArrayList<>(ticketRepository.findAllById(indexes));
        tickets.forEach(ticket -> ticket.setBooking(Status.BOUGHT));
        ticketRepository.saveAll(tickets);
        return tickets;
    }

    @Override
    public ArrayList<Ticket> getAllTicketsBySessionId(Long session_id) {
        return new ArrayList<>(ticketRepository.findAllBySessionId(session_id));
    }

    @Override
    public void deleteTikets(ArrayList<Ticket> tickets) {
        ticketRepository.deleteAll(tickets);
    }

    @Override
    public void saveTickets(List<Ticket> tickets) {
        ticketRepository.saveAll(tickets);
    }
}
