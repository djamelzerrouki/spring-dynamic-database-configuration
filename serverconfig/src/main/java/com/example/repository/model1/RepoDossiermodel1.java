package com.example.repository.model1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.model1.Dossier;

@Repository
public interface RepoDossiermodel1 extends JpaRepository<Dossier, Long>{

}
