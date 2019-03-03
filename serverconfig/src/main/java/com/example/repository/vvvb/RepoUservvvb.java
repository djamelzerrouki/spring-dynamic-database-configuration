package com.example.repository.vvvb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface RepoUservvvb extends JpaRepository<User, Long>{

}
