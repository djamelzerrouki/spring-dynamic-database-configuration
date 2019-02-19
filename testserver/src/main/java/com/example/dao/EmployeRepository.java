package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.entites.Employe;
 public interface EmployeRepository 
extends JpaRepository<Employe,Long>{
	
 public List<Employe> findByName(String name);
 public List<Employe> findAll();
 
}
