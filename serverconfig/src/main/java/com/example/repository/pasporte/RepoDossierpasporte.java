package com.example.repository.pasporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.pasporte.Dossier;

@Repository
public interface RepoDossierpasporte extends JpaRepository<Dossier, Long>{

}
