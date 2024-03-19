package com.example.onlinecinema.domain.movie;

import com.example.onlinecinema.domain.session.MovieSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "title", unique = true)
    private String title;

    private String director;

    private String poster;

    private int durationMinutes;

    @OneToMany(cascade={CascadeType.ALL},
            orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="movie_id", updatable=true)
    private List<MovieSession> movieSessions;
}
