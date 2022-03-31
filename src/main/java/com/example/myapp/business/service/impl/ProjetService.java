package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IProjetService;
import com.example.myapp.persistence.model.Projet;
import com.example.myapp.persistence.repository.ProjetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Service
public class ProjetService implements IProjetService {
    public final ProjetRepository projetRepository;

    @Override
    public List<Projet> getListProjet() {
        try {
            return projetRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error ProjetService in method getListProjet :: " + e.toString());

        }
    }

    @Override
    public Projet getProjetById(Long id){
        try {
            if (id == null)
                return new Projet();
            Projet d = projetRepository.findProjetById(id);
            if (d == null)
                return new Projet();
            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error ProjetService in method getProjetById :: " + e.toString());
        }

    }


    @Override
    public Projet addProjet(Projet projet) {
        try {
            Projet objNomUnique = projetRepository.findProjetByNom(projet.getNom());

            if ( objNomUnique != null)
                throw new IllegalStateException("Projet name token");

            projet.setDatecreation(new Timestamp(new Date().getTime()));

            return projetRepository.save(projet);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method addProjet :: " + e.toString());
        }
    }


    @Transactional
    @Override
    public Projet updateProjet(Projet projet,Long id) {
        try {
            Projet upproj = projetRepository.findProjetById(id);
            upproj.setNom(projet.getNom());
            upproj.setDatedebut(projet.getDatedebut());
            upproj.setDatefin(projet.getDatefin());
            upproj.setDescription(projet.getDescription());
            upproj.setPriorite(projet.getPriorite());
            upproj.setDateupdate(new Timestamp(new Date().getTime()));
            upproj.setId(id);

            return projetRepository.save(upproj);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method updateProjet :: " + e.toString());
        }
    }

    @Transactional
    @Override
    public void deleteProjet(Long id) {
        try {
            projetRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method deleteProjet :: " + e.toString());
        }
    }



}
