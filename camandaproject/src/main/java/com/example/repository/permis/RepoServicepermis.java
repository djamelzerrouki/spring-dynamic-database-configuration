package com.example.repository.permis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicepermis extends JpaRepository<Service, Long>{

}
