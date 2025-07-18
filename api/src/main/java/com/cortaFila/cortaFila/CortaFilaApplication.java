package com.cortaFila.cortaFila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CortaFilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CortaFilaApplication.class, args);
	}
}
