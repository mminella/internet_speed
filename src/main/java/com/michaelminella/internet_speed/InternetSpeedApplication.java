package com.michaelminella.internet_speed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InternetSpeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetSpeedApplication.class, args);
	}
}
