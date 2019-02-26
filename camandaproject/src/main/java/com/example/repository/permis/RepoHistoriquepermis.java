package com.example.repository.permis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquepermis extends JpaRepository<Historique, Long>{

}
