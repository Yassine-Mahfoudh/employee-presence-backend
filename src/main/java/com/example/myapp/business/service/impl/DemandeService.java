package com.example.myapp.business.service.impl;


import com.example.myapp.business.service.IDemandeService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.repository.DemandeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
@Slf4j
public class DemandeService implements IDemandeService {
    public final DemandeRepository demandeRepository;

    @Override
    public List<Demande> getListDemande() {
        try {
            log.info("Fetching all demands ");
            return demandeRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error DemandeService in method getListDemande :: " + e.toString());

        }
    }

    @Override
    public Demande getDemandeById(Long id){
        try {
            if (id == null)
                return new Demande();
            Demande d = demandeRepository.findDemandeById(id);
            if (d == null)
                return new Demande();
            log.info("Fetching demand with id :{} ",id);
            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error DemandeService in method getDemandeById :: " + e.toString());
        }

    }


    @Override
    public Demande addDemande(Demande dem) {
        try {
            Demande objNomUnique = demandeRepository.findDemandeByName(dem.getName());

            if ( objNomUnique != null)
                throw new IllegalStateException("Demande name token");

                dem.setCreationdate(new Timestamp(new Date().getTime()));
            log.info("Saving new demade {} to the databse ",dem.getName());
            return demandeRepository.save(dem);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method addDemande :: " + e.toString());
        }
    }

    @Override
    public Demande updateDemandeById(Demande demande,Long id) {
        try {
            Demande updem = demandeRepository.findDemandeById(demande.getId());
            updem.setName(demande.getName());
            updem.setMotive(demande.getMotive());
            updem.setUpdatedate(new Timestamp(new Date().getTime()));
            updem.setId(id);
            log.info("updating demande {} to the database ",demande.getName());

            return demandeRepository.save(updem);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method updateDemandeById :: " + e.toString());
        }
    }

    @Override
    public void deleteDemandeById(Long id) {
        try {
            log.info("Deleting demand with id {}  ",id);
            demandeRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method deleteDemandeById :: " + e.toString());
        }
    }
}
