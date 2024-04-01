package com.example.onlinecinema.service.impl;

import com.example.onlinecinema.service.interfaces.MovieSessionService;
import com.example.onlinecinema.service.interfaces.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final MovieSessionService movieSessionService;
    @Scheduled(cron = "0 0 * * * *")
    @Override
    public void deletePastSessions() {
        movieSessionService.deletePastSession();
    }
}
