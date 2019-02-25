package com.example;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaThymeleafSpringbootWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaThymeleafSpringbootWebappApplication.class, args);
	}
}
