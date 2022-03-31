package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Projet;

import java.util.List;

public interface IProjetService {
    public List<Projet> getListProjet();
    public Projet addProjet(Projet projet);
    public void deleteProjet(Long id);
    public Projet updateProjet(Projet projet,Long id);
    public Projet getProjetById(Long id);
}
