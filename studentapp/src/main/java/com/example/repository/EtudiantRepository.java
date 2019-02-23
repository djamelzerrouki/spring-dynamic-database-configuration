package com.example.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.modele.Etudiant;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{
}
