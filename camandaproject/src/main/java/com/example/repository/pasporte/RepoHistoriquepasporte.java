package com.example.repository.pasporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquepasporte extends JpaRepository<Historique, Long>{

}
