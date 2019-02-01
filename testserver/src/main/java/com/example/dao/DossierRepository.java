package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entites.Dossier;

public interface DossierRepository 
extends JpaRepository<Dossier,Long>{
	
 public Optional<Dossier> findById(Long id);
 public List<Dossier> findAll();
 
}
