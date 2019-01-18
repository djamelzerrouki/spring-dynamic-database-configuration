package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entites.Enployee;

public interface EnployeeRepository 
extends JpaRepository<Enployee,Long>{
	
 public List<Enployee> findByName(String name);
 public List<Enployee> findAll();
 
}
