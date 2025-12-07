package com.erenturhan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.erenturhan"})
@SpringBootApplication
@ComponentScan(basePackages = {"com.erenturhan"})
@EnableJpaRepositories(basePackages = {"com.erenturhan"})
public class Derss1ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(Derss1ApplicationStarter.class, args);
	}

}
