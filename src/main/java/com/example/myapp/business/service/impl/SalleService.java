package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IDemandeService;
import com.example.myapp.business.service.ISalleService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.model.Salle;
import com.example.myapp.persistence.repository.DemandeRepository;
import com.example.myapp.persistence.repository.SalleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Service
public class SalleService implements ISalleService {
    public final SalleRepository salleRepository;


    @Override
    public List<Salle> getListSalle() {
        try {
            return salleRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error SalleService in method getListSalle :: " + e.toString());

        }
    }

    @Override
    public Salle getSalleById(Long id){
        try {
            if (id == null)
                return new Salle();
            Salle s = salleRepository.findSalleById(id);
            if (s == null)
                return new Salle();
            return s;
        } catch (Exception e){
            throw new IllegalStateException("Error SalleService in method getSalleById :: " + e.toString());
        }

    }


    @Override
    public Salle addSalle(Salle salle) {
        try {
            Salle objNomUnique = salleRepository.findSalleByNum(salle.getNum());

            if ( objNomUnique != null)
                throw new IllegalStateException("Salle name token");

            salle.setDatecreation(new Timestamp(new Date().getTime()));

            return salleRepository.save(salle);
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleService in method addSalle :: " + e.toString());
        }
    }
    @Transactional
    @Override
    public Salle updateSalle(Salle salle,Long id) {
        try {
            Salle ups = salleRepository.findSalleById(salle.getId());
            ups.setNum(salle.getNum());
            ups.setNbposte(salle.getNbposte());
            ups.setType(salle.getType());
            ups.setPourcentagePres(salle.getPourcentagePres());
            ups.setDateupdate(new Timestamp(new Date().getTime()));
            ups.setId(id);
            return salleRepository.save(ups);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error SalleService in method updateSalle :: " + e.toString());
        }
    }

    @Transactional
    @Override
    public void deleteSalle(Long id) {
        try {
            salleRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error SalleService in method deleteSalle :: " + e.toString());
        }
    }
}
