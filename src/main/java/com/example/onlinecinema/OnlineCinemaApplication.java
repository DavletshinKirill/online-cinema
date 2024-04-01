package com.example.onlinecinema;

import com.example.onlinecinema.config.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class OnlineCinemaApplication {

    private static Initializer initiator;

    @Autowired
    public void setInitialLoader(Initializer initiator) {
        OnlineCinemaApplication.initiator = initiator;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineCinemaApplication.class, args);
        initiator.initialize();
    }

}
