package com.example.repository.permis;
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
private boolean ch4 ;
private boolean ch5 ;
private boolean ch6 ;
}
