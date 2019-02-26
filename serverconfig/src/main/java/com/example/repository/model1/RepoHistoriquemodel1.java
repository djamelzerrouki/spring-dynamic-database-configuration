package com.example.repository.model1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Historique;

@Repository
public interface RepoHistoriquemodel1 extends JpaRepository<Historique, Long>{

}
