package ru.kata.rodriguez.jpp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		System.out.println("To get started, open the page http://localhost:8080/auth/registration");
	}
}