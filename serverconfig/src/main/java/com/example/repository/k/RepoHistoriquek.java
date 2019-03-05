package com.example.repository.k;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquek extends JpaRepository<Historique, Long>{

}
