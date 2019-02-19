package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.entites.Service;
 public interface ServiceRepository 
extends JpaRepository<Service,Long>{
	
 public List<Service> findByName(String name);
 public List<Service> findAll();
 
}
