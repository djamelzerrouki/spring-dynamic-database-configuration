package com.example.repository.model1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employe;

@Repository
public interface RepoEmployemodel1 extends JpaRepository<Employe, Long>{

}
