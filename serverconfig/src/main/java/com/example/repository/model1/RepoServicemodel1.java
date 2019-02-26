package com.example.repository.model1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicemodel1 extends JpaRepository<Service, Long>{

}
