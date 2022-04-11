package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IDepartementService;
import com.example.myapp.persistence.model.Departement;
import com.example.myapp.persistence.repository.DepartementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class DepartementService implements IDepartementService {

    public final DepartementRepository departementRepository;

    @Override
    public List<Departement> getListDepartement() {
        try {
            return departementRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error DepartementService in method getListDepartement :: " + e.toString());
        }
    }

    @Override
    public Departement getDepartementById(Long id){
        try {
            if (id == null)
                return new Departement();
            Departement d = departementRepository.findDepartementById(id);
            if (d == null)
                return new Departement();
            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error DepartementService in method getDepartementById :: " + e.toString());
        }

    }
    @Override
    public Departement addDepartement(Departement dep) {
        try {
            Departement depNomUnique = departementRepository.findDepartementByName(dep.getName());

            if ( depNomUnique != null)
                throw new IllegalStateException("Departement name token");

            dep.setCreationdate(new Timestamp(new Date().getTime()));

            return departementRepository.save(dep);
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementService in method addDepartement :: " + e.toString());
        }
    }
    @Transactional
    @Override
    public Departement updateDepartementById(Departement departement,Long id) {
        try {
            Departement updep = departementRepository.findDepartementById(id);
            updep.setName(departement.getName());
            updep.setNbsalles(departement.getNbsalles());
            updep.setUpdatedate(new Timestamp(new Date().getTime()));
            updep.setId(id);

            return departementRepository.save(updep);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DepartementService in method updateDepartementById :: " + e.toString());
        }
    }

    @Transactional
    @Override
    public void deleteDepartementById(Long id) {
        try {
            departementRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method deleteDepartementById :: " + e.toString());
        }
    }
}
