package com.example.repository.vvvb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Dossier;

@Repository
public interface RepoDossiervvvb extends JpaRepository<Dossier, Long>{

}
