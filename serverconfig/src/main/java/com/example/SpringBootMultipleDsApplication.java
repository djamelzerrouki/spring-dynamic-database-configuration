package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.BookDBConfig;
import com.example.model.Book;
import com.example.model.User;
  
@SpringBootApplication
@RestController
public class SpringBootMultipleDsApplication {

	@Autowired(required=true)
	private com.example.user.repository.BookRepository bookRepository1;
	@Autowired(required=true)
	private com.example.book.repository.BookRepository2 bookRepository2;

	@Autowired(required=true)
	  private com.example.user.repository.UserRepository userRepository1;
	@Autowired(required=true)
	  private com.example.book.repository.UserRepository2 userRepository2;
	

	 @PostConstruct
	public void addData2DB() {
		userRepository1.saveAll(Stream.of(new User(744, "John"), new User(455, "Pitter")).collect(Collectors.toList()));
		bookRepository1.saveAll(
				Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
		userRepository2.saveAll(Stream.of(new User(744, "John"), new User(455, "Pitter")).collect(Collectors.toList()));
		bookRepository2.saveAll(
				Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
	
	 } 

	@GetMapping("/getUsers")
	public List<User> getUsers() {
 			return userRepository1.findAll();
 
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return bookRepository1.findAll();
	}

	@GetMapping("/getUsers2")
	public List<User> getUsers2() {
 			return userRepository2.findAll();
 
	}

	@GetMapping("/getBooks2")
	public List<Book> getBooks2() {
		return bookRepository2.findAll();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDsApplication.class, args);
	}
}
