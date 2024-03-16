package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.domain.movie.Movie;
import com.example.onlinecinema.domain.session.MovieSession;
import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.service.interfaces.MailService;
import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    private final JavaMailSender javaMailSender;

    public void sendRegistrationEmail(UserEntity user, Properties properties) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Thank you for your registration" + user.getUsername());
        helper.setTo(user.getUsername());
        String emailContent = getRegistrationEmailContent(user.getUsername());
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationEmailContent(String name) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        configuration.getTemplate("register.fthl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }

    public void bookTicket(UserEntity user, Movie movie, MovieSession movieSession, Properties properties) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
        helper.setSubject("Билет заказан");
        helper.setTo(user.getUsername());
        String emailContent = getBookTicketEmailContent(user.getUsername(), properties);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }
    @SneakyThrows
    private String getBookTicketEmailContent(String name, Properties properties) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        model.put("title", properties.getProperty("movie.title"));
        model.put("date",  properties.getProperty("movieSession.date_time"));
        model.put("price",  properties.getProperty("movieSession.price"));
        configuration.getTemplate("bookTicket.fthl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
