package com.example.onlinecinema.domain.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@Data
public class Ticket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private Status booking;


    public Ticket(Status status) {
        booking = status;
    }
}
