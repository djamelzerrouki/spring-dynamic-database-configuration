package com.example.repository.k;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface RepoUserk extends JpaRepository<User, Long>{

}
