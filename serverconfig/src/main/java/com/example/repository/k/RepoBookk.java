package com.example.repository.k;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface RepoBookk extends JpaRepository<Book, Long>{

}
