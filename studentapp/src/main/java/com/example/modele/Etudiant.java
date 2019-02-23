package com.example.modele;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Etudiant implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	private Long id;
	private String name;
	private String password;
 	@DateTimeFormat(pattern="yyyy-MM-dd")   
	private Date datenes;
 	@OneToMany(mappedBy = "etudiant")
 	private Collection<Note> notes;


}
