package com.lab05.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.controller")
@EnableJpaRepositories(basePackages = "com.DAO")
@EntityScan(basePackages = "com.entity")  // Add this if your entities are in 'com.entity' package

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
