package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.cartenational.Dossier;

@Repository
public interface RepoDossiercartenational extends JpaRepository<Dossier, Long>{

}
