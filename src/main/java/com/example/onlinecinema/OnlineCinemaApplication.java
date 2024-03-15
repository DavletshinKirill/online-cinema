package com.example.onlinecinema;

import com.example.onlinecinema.config.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
