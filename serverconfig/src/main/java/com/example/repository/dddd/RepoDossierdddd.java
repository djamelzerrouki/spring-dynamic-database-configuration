package com.example.repository.dddd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Dossier;

@Repository
public interface RepoDossierdddd extends JpaRepository<Dossier, Long>{

}
