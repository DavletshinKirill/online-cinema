package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.exeption.ResourceNotFoundException;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.domain.ticket.Ticket;
import com.example.onlinecinema.repository.MovieSessionRepository;
import com.example.onlinecinema.service.interfaces.MovieSessionService;
import com.example.onlinecinema.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final TicketService ticketService;


    @Override
    public MovieSession update(MovieSession movieSession) {

        MovieSession movieValidated = getMovieSessionById(movieSession.getId());
        movieValidated.setPrice(movieSession.getPrice());
        movieValidated.setDate_start(movieSession.getDate_start());

        if(movieSession.getRowsAmount() * movieSession.getSeatsInRow() != movieValidated.getRowsAmount() * movieSession.getSeatsInRow()) {
            ArrayList<Ticket> tickets = ticketService.getAllTicketsBySessionId(movieValidated.getId());
            ticketService.deleteTikets(tickets);
            movieValidated.setRowsAmount(movieSession.getRowsAmount());
            movieValidated.setSeatsInRow(movieSession.getSeatsInRow());
            movieValidated.createTickets();
            ticketService.saveTickets(movieValidated.getTickets());
        }

        return movieSessionRepository.save(movieValidated);
    }

    @Override
    public void deleteById(Long id) {
        movieSessionRepository.deleteById(id);
    }

    @Override
    public MovieSession getMovieSessionById(Long id) {
        return movieSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie Session by id not found exception"));
    }

    @Override
    public List<MovieSession> getAllMovieSessionsByMovieId(Long id) {
        return movieSessionRepository.findAllByMovieId(id);
    }

    @Override
    public void deletePastSession() {
        List<MovieSession> sessions = movieSessionRepository.findPastSession(LocalDateTime.now());
        movieSessionRepository.deleteAll(sessions);
    }
}
