package com.example.repository.djamel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.djamel.Dossier;

@Repository
public interface RepoDossierdjamel extends JpaRepository<Dossier, Long>{

}
