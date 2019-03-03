package com.example.repository.dddd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployedddd extends JpaRepository<Employe, Long>{

}
