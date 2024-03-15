package com.example.onlinecinema.repository;

import com.example.onlinecinema.domain.session.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {
    @Query(value = """
                    SELECT * FROM movie_session ms
                    WHERE ms.movie_id = :movieId
            """, nativeQuery = true)
    List<MovieSession> findAllByMovieId(@Param("movieId") Long movieId);

    @Query(value = """
                    SELECT * FROM movie_session ms
                    WHERE ms.date_start < :now
            """, nativeQuery = true)
    List<MovieSession> findPastSession(@Param("now") LocalDateTime now);

}
