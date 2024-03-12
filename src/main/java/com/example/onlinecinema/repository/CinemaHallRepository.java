package com.example.onlinecinema.repository;

import com.example.onlinecinema.domain.session.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
}
