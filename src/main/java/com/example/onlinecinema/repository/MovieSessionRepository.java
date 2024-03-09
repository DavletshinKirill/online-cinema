package com.example.onlinecinema.repository;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
}
