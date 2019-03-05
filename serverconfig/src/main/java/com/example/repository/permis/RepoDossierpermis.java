package com.example.repository.permis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.repository.permis.Dossier;

@Repository
public interface RepoDossierpermis extends JpaRepository<Dossier, Long>{

}
