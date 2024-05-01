package com.social.eventservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EventServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
	}

}
