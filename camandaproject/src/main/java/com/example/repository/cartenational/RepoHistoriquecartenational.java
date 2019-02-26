package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquecartenational extends JpaRepository<Historique, Long>{

}
