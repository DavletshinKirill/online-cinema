package com.example.onlinecinema.domain.session;

import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@Table(name = "movie_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieSession implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowsAmount;

    private int seatsInRow;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cinema_hall_id", updatable = true)
    private List<Ticket> tickets;

    @Column(name = "date_start")
    private LocalDateTime date_start;

    private Float price;

    public void createTickets() {
        tickets = IntStream.range(0, rowsAmount * seatsInRow)
                .mapToObj(ticket -> new Ticket(Status.NONE))
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
