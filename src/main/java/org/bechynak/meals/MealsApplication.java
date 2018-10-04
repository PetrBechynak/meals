package org.bechynak.meals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealsApplication.class, args);
	}
}
