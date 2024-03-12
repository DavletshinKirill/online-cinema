package com.example.onlinecinema.domain.session;

import com.example.onlinecinema.domain.BaseEntity;
import com.example.onlinecinema.domain.ticket.Status;
import com.example.onlinecinema.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cinema_hall")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CinemaHall extends BaseEntity {
    @CollectionTable(name = "ticket")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private ArrayList<Ticket> tickets;
    private int amountRows;
    private int seatsInRow;

    public CinemaHall(int amountRows, int seatsInRow) {
        this.amountRows = amountRows;
        this.seatsInRow = seatsInRow;

        tickets = IntStream.range(0, amountRows * seatsInRow)
                .mapToObj(ticket -> new Ticket(Status.NONE))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void BookTicket(int index) {
        tickets.get(index).setBooking(Status.BOOKED);
    }

    public void SellTicket(int index) {
        tickets.get(index).setBooking(Status.BOUGHT);
    }
}
