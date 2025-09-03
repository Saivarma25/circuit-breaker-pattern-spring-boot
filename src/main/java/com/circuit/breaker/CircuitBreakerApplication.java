package com.circuit.breaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application for Circuit Breaker with Resilience4J
 *
 * @author Saivarma akarapu
 */
@SpringBootApplication
public class CircuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerApplication.class, args);
	}

}
