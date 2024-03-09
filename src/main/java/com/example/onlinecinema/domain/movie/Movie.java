package com.example.onlinecinema.domain.movie;

import com.example.onlinecinema.domain.BaseEntity;
import com.example.onlinecinema.domain.session.MovieSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie extends BaseEntity {

    @Column(name = "title", unique = true)
    private String title;

    private String director;

    private String poster;

    private int durationMinutes;

    @CollectionTable(name = "movie_session")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "movie_session_id"))
    private List<MovieSession> movieSessions;
}
