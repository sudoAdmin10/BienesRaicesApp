package com.um;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Prueba2Application {

	public static void main(String[] args) {
		SpringApplication.run(Prueba2Application.class, args);
	}

	public class AppConfig {
		@Bean
		public Random random() {
			return new Random();
		}
	}
}
