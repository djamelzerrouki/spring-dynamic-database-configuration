package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Book;
import com.example.model.Employe;
import com.example.model.User;
  
@SpringBootApplication
@RestController
public class SpringBootMultipleDsApplication {

	private  static String test;

	@Autowired(required=true)
	private com.example.user.repository.BookRepository bookRepository1;
	


	@Autowired(required=true)
	  private com.example.user.repository.UserRepository userRepository1;
	@Autowired(required=true)
	  private com.example.book.repository.UserRepository2 userRepository2;
	
	@Autowired(required=true)
	private  com.example.repository.djamel.RepoEmployedjamel RepoEmployedjamel ;
	@Autowired(required=true)
	private  com.example.repository.dddd.RepoEmployedddd RepoEmployedddd ;

	@RequestMapping("/employe")
	public List<Employe> gethUsers( ) {
		 	return  RepoEmployedjamel.findAll();

		}
			 
		@RequestMapping("/employedddd")
		public List<Employe> gethUjhsers() {
			 
	 			return  RepoEmployedddd.findAll();

		}
				 
	
	
	 @PostConstruct
	public void addData2DB() {
		userRepository1.saveAll(Stream.of(new User(744L, "John"), new User(455L, "Pitter")).collect(Collectors.toList()));
		bookRepository1.saveAll(
				Stream.of(new Book(111L, "Core Java"), new Book(222L, "Spring Boot")).collect(Collectors.toList()));
		userRepository2.saveAll(Stream.of(new User(744L, "John"), new User(455L, "Pitter")).collect(Collectors.toList()));
	/*	bookRepository2.saveAll(
				Stream.of(new Book(111L, "Core Java"), new Book(222L, "Spring Boot")).collect(Collectors.toList()));
	*/
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

	 
	
	public static void main(String[] args) {
	
 		SpringApplication.run(SpringBootMultipleDsApplication.class, args);
	
	}
}
