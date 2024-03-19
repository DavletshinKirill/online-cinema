package com.example.onlinecinema.service.interfaces;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.domain.user.UserEntity;
import jakarta.mail.MessagingException;

import java.util.Properties;

public interface MailService {
    void sendRegistrationEmail(UserEntity user, Properties properties) throws MessagingException ;
    void bookTicket(UserEntity user, Movie movie, MovieSession movieSession) throws MessagingException ;
}
