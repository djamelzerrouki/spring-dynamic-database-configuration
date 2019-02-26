package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployecartenational extends JpaRepository<Employe, Long>{

}
