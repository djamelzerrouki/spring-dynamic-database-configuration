package com.example.repository.dddd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface RepoBookdddd extends JpaRepository<Book, Long>{

}
