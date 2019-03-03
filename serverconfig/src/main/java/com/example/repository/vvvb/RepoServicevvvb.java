package com.example.repository.vvvb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Service;

@Repository
public interface RepoServicevvvb extends JpaRepository<Service, Long>{

}
