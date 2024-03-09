package com.example.onlinecinema.domain.session;

import com.example.onlinecinema.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "movie_session")
@Data
@NoArgsConstructor
public class MovieSession extends BaseEntity {

    @CollectionTable(name = "cinemaHall")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "cinemaHall_id"))
    private CinemaHall cinemaHall;

    @Column(name = "date_start")
    private LocalDateTime date_start;

}
