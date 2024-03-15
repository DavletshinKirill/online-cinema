package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface TicketService {
    ArrayList<Ticket> bookTicket(ArrayList<Long> indexes);
    ArrayList<Ticket> buyTicket(ArrayList<Long> indexes);

    ArrayList<Ticket> getAllTicketsBySessionId(Long session_id);

    void deleteTikets(ArrayList<Ticket> tickets);

    void saveTickets(List<Ticket> tickets);

}
