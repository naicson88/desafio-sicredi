package com.sicredi.desafio;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
		TimeZone zone = TimeZone.getDefault();
        System.out.println(zone.getDisplayName());
        System.out.println(zone.getID());
	}

}
