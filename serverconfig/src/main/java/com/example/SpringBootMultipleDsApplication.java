package com.example;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
  
@SpringBootApplication
//@EnableAutoConfiguration
 public class SpringBootMultipleDsApplication {
	//@RestController
 
	public static void main(String[] args) {
	//    System.setProperty("spring.devtools.restart.enabled", "false");

 		SpringApplication.run(SpringBootMultipleDsApplication.class, args);
	
	}
	
}
