package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.repository.TicketRepository;
import com.example.onlinecinema.service.interfaces.MailService;
import com.example.onlinecinema.service.interfaces.MovieSessionService;
import com.example.onlinecinema.service.interfaces.TicketService;
import com.example.onlinecinema.service.interfaces.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final MailService mailService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    @Override
    public ArrayList<Ticket> bookTicket(ArrayList<Long> indexes) throws MessagingException {

        ArrayList<Ticket> tickets = new ArrayList<>(ticketRepository.findAllById(indexes));
        tickets.forEach(ticket -> ticket.setBooking(Status.BOOKED));
        ticketRepository.saveAll(tickets);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.getUserByUsername(username);
        MovieSession movieSession = getByTicketId(tickets.getFirst().getId());
        Movie movie = movieSessionService.getMovieByMovieSessionId(movieSession.getId());
        mailService.bookTicket(user, movie, movieSession);
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
    public MovieSession getByTicketId(Long id) {
        Long session_id = ticketRepository.findMovieSessionIdByTicketId(id);
        return movieSessionService.getMovieSessionById(session_id);
    }

    @Override
    public void deleteTickets(ArrayList<Ticket> tickets) {
        ticketRepository.deleteAll(tickets);
    }

    @Override
    public void saveTickets(List<Ticket> tickets) {
        ticketRepository.saveAll(tickets);
    }
}
