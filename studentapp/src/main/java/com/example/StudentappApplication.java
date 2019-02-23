package com.example;

import com.example.modele.Etudiant;
import com.example.modele.Module;
import com.example.modele.Note;
import com.example.repository.EtudiantRepository;
import com.example.repository.ModuleRepository;
import com.example.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StudentappApplication implements CommandLineRunner {
	private static ConfigurableApplicationContext context;

	@Autowired(required=true)
	private    EtudiantRepository etudiantRepository;
	@Autowired(required=true)
	private   ModuleRepository moduleRepository;

	@Autowired(required=true)
	private   NoteRepository noteRepository;



	public static void main(String[] args) {
	  SpringApplication.run(StudentappApplication.class, args);
 

	}

	@Override
	public void run(String... args) throws Exception {
		Etudiant e1=new Etudiant(null,"Djamel","1234",new Date(), null);
		Etudiant e2=new Etudiant(null,"jimmi","1234",new Date(),null);
		Etudiant e3=new Etudiant(null,"zerrouki","1234",new Date(),null);
		Etudiant e4=new Etudiant(null,"mohemmad","1234",new Date(),null);

		etudiantRepository.saveAll(Stream.of(e1,e2,e3,e4).collect(Collectors.toList()));
		Module m1=new Module(null,"Algorithme",3,null);
		Module m2=new Module(null,"SMA",3,null);
		Module m3=new Module(null,"POO",3,null);

		moduleRepository.saveAll(Stream.of(m1,m2,m3).collect(Collectors.toList()));



		 noteRepository.save(new Note(null,e1,m2,12.3));
		 noteRepository.save(new Note(null,e2,m2,13.5));
		 noteRepository.save(new Note(null,e2,m3,15.75));
		 noteRepository.save(new Note(null,e4,m1,17.0));
		 noteRepository.save(new Note(null,e3,m1,18));

	}
}
