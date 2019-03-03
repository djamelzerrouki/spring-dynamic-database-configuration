package com.example.repository.dddd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquedddd extends JpaRepository<Historique, Long>{

}
