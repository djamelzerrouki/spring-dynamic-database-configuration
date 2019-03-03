package com.example.repository.dddd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface RepoUserdddd extends JpaRepository<User, Long>{

}
