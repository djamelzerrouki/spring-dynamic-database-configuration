package com.example.repository.pasporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicepasporte extends JpaRepository<Service, Long>{

}
