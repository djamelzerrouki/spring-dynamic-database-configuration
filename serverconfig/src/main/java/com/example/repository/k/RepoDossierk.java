package com.example.repository.k;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Dossier;

@Repository
public interface RepoDossierk extends JpaRepository<Dossier, Long>{

}
