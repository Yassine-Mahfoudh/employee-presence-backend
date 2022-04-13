package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IDemandeService;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Demande> getListDemande() {
        try {
            return iDemandeService.getListDemande();
        } catch (Exception e) {
//            throw new ApiRequestException("Error DemandeController in method getListDemande with custom exception");
            throw new IllegalStateException("Error DemandeController in method getListDemande :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demande> getDemandeById(@PathVariable("id") Long id) {
        try {
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
            return iDemandeService.addDemande(demande);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method addDemande :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demande> updateDemandeById(@RequestBody Demande demande) {
        try {
            Demande updateDemande = iDemandeService.updateDemandeById(demande);
            return new ResponseEntity<>(updateDemande, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method updateDemandeById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDemandeById(@PathVariable("id") Long id) {
        try {
            iDemandeService.deleteDemandeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method deleteDemandeById :: " + e.toString());
        }
    }

}

