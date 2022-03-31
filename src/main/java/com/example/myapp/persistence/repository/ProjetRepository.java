package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
    @Query("select d from Projet d where d.id=:id ")
    public Projet findProjetById(@Param("id") Long id);

    @Query("select d from Projet d where d.nom=:nom ")
    public Projet findProjetByNom(@Param("nom") String nom);
}
