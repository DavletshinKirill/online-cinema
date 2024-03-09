package com.example.onlinecinema.repository;

import com.example.onlinecinema.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
