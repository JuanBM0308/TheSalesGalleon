package com.juanba.the_sales_galleon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheSalesGalleonApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TheSalesGalleonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\nWelcome to the sales galleon developer!\n");
	}
}
