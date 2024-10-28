package com.taskiran.footballapibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.taskiran.footballapibackend")
public class FootballApiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballApiBackendApplication.class, args);
	}

}
