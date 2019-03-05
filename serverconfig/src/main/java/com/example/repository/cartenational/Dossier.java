package com.example.repository.cartenational;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dossier {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="id")
private Long id ;
private String nom ;
private String prenom ;
private String tlphon ;
private boolean ch1 ;
private boolean ch2 ;
private boolean ch3 ;
}
