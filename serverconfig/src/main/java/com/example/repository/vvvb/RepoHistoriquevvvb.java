package com.example.repository.vvvb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquevvvb extends JpaRepository<Historique, Long>{

}
