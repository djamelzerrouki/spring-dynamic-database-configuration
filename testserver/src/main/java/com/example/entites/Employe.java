package com.example.entites;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Employe {

	@NotEmpty
    @Size(min=2, max=30)
	private String name;
	private String password;
	private String tlphon;
	private Long servicid;
	@DateTimeFormat(pattern="yyyy-MM-dd")   
    private Date datenes;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
   
 
}
