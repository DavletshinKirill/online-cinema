package com.example.onlinecinema.domain.ticket;

import com.example.onlinecinema.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@Data
public class Ticket extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Status booking;
}
