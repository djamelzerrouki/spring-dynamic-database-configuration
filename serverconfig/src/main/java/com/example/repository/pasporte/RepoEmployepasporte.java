package com.example.repository.pasporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployepasporte extends JpaRepository<Employe, Long>{

}
