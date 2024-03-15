package com.example.onlinecinema.repository;

import com.example.onlinecinema.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = """
                    SELECT * FROM tickets t 
                    WHERE t.cinema_hall_id = :sessionId
            """, nativeQuery = true)
    List<Ticket> findAllBySessionId(@Param("sessionId") Long sessionId);
}

