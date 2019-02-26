package com.example.repository.djamel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicedjamel extends JpaRepository<Service, Long>{

}
