package com.example.repository.djamel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquedjamel extends JpaRepository<Historique, Long>{

}
