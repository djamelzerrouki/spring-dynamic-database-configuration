package com.example.repository.k;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployek extends JpaRepository<Employe, Long>{

}
