package com.example.repository.k;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.k.Dossier;

@Repository
public interface RepoDossierk extends JpaRepository<Dossier, Long>{

}
