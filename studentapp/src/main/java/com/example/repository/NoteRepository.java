package com.example.repository;


import com.example.modele.Etudiant;
import com.example.modele.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note,Long>{
}
