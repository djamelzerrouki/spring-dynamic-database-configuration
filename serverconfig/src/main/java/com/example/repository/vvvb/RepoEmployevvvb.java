package com.example.repository.vvvb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployevvvb extends JpaRepository<Employe, Long>{

}
