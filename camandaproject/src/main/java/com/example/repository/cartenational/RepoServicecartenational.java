package com.example.repository.cartenational;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicecartenational extends JpaRepository<Service, Long>{

}
