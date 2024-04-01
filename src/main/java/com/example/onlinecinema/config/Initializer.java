package com.example.onlinecinema.config;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.domain.user.Role;
import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.service.interfaces.MovieService;
import com.example.onlinecinema.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final UserService userService;
    private final MovieService movieService;

    private UserEntity createUser(Long id, String username, String password) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        roles.add(Role.ROLE_USER);
        UserEntity user = new UserEntity(id, username, password, roles);
        user.setId(id);
        return userService.create(user);
    }

    private Movie createMovie(Long id, String title, String director, String poster, int durationMinutes, int year,
                              String actors, String country, String genre, String description) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setPoster(poster);
        movie.setDurationMinutes(durationMinutes);
        movie.setPoster(actors);
        movie.setCountry(country);
        movie.setYear(year);
        movie.setGenre(genre);
        movie.setDescription(description);
        return movieService.create(movie);
    }


    private MovieSession addMovieSession(Movie movie, MovieSession movieSession){
        return movieService.createMovieSession(movie.getId(), movieSession);
    }

    public void initialize() {
        UserEntity user1 = createUser((long)1 , "kirichka27@gmail.com", "123456");
        UserEntity user2 = createUser((long)2, "mikesmith@yahoo.com", "123456");

        Movie movie1 = createMovie((long)1, "Темный рыарь", "Кристофер Нолан", "Пока не сделал",
                152, 2008, "Кристиан Бэйл, Хит Лэйджер, Морган Фримен, Гари Олдман",
                "США, Великобритания", "Боевик, триллер, драма",
                "Бэтмен поднимает ставки в войне с криминалом. С помощью лейтенанта Джима Гордона и прокурора Харви Дента он намерен очистить улицы от преступности, отравляющей город.");
        Movie movie2 = createMovie((long)2, "Начало", "Кристофер Нолан", "Пока не сделал",
                148, 2010, "Леонардо ДиКаприо", "США, Великобритания",
                "Триллер, драма, фантастика, боевик",
                "Кобб — талантливый вор, лучший из лучших в опасном искусстве извлечения: он крадет ценные секреты из глубин подсознания во время сна, когда человеческий разум наиболее уязвим.");

        MovieSession movieSession = new MovieSession();
        movieSession.setDate_start(LocalDateTime.of(2022, 12, 31, 23, 59, 59));
        movieSession.setPrice(500F);
        movieSession.setSeatsInRow(5);
        movieSession.setRowsAmount(5);
        movieSession.setFormat("2D");

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setDate_start(LocalDateTime.of(2024, 3, 16, 19, 48, 59));
        movieSession1.setPrice(600F);
        movieSession1.setSeatsInRow(6);
        movieSession1.setRowsAmount(6);
        movieSession1.setFormat("3D");

        addMovieSession(movie1, movieSession);
        addMovieSession(movie2, movieSession1);

    }
}
