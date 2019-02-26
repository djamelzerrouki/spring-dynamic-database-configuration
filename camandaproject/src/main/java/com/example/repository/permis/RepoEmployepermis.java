package com.example.repository.permis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployepermis extends JpaRepository<Employe, Long>{

}
