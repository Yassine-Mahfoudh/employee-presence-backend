package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IDemandeService;
import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.impl.UtilisateurService;
import com.example.myapp.persistence.model.Demande;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "/demande")
@AllArgsConstructor
public class DemandeController {
    private final IDemandeService iDemandeService;
    private final ILogDataService iLogDataService;
    private final UtilisateurService utilisateurService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Demande> getListDemande() {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Get List Of Demand");
            return iDemandeService.getListDemande();


        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method getListDemande :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demande> getDemandeById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Get Demand Num : "+id);
            Demande demande = iDemandeService.getDemandeById(id);
            return new ResponseEntity<>(demande, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method getDemandeById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Demande addDemande(@RequestBody Demande demande) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Add new Demand");
            return iDemandeService.addDemande(demande);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method addDemande :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demande> updateDemandeById(@RequestBody Demande demande, @PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Update Demande Num : " +demande.getId());
            Demande updateDemande = iDemandeService.updateDemandeById(demande,id);
            return new ResponseEntity<>(updateDemande, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method updateDemandeById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDemandeById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Delete Demande Num : "+id);
            iDemandeService.deleteDemandeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method deleteDemandeById :: " + e.toString());
        }
    }

}

