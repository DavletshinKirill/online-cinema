package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.domain.ticket.Ticket;
import jakarta.mail.MessagingException;

import java.util.ArrayList;
import java.util.List;

public interface TicketService {
    ArrayList<Ticket> bookTicket(ArrayList<Long> indexes) throws MessagingException;
    ArrayList<Ticket> buyTicket(ArrayList<Long> indexes);

    ArrayList<Ticket> getAllTicketsBySessionId(Long session_id);

    MovieSession getByTicketId(Long id);
    void deleteTickets(ArrayList<Ticket> tickets);

    void saveTickets(List<Ticket> tickets);

}
