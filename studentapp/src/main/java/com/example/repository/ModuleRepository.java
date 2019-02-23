package com.example.repository;


import com.example.modele.Etudiant;
import com.example.modele.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ModuleRepository extends JpaRepository<Module,Long>{
}
