package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IProjetService;
import com.example.myapp.persistence.model.Projet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/projet")
@AllArgsConstructor
public class ProjetController {
    private final IProjetService iProjetService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Projet> getListProjet() {
        try {
            return iProjetService.getListProjet();
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method getListProjet :: " + e.toString());
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projet> getProjetById(@PathVariable("id") Long id) {
        try {
            Projet projet = iProjetService.getProjetById(id);
            return new ResponseEntity<>(projet, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method getProjetById :: " + e.toString());
        }
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Projet addProjet(@RequestBody Projet projet) {
        try {
            return iProjetService.addProjet(projet);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method addProjet :: " + e.toString());
        }
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Projet> updateProjet(@RequestBody Projet projet, @PathVariable("id") Long id) {
        try {
            Projet updateProjet = iProjetService.updateProjet(projet, id);
            return new ResponseEntity<>(updateProjet, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method updateProjet :: " + e.toString());
        }
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProjet(@PathVariable("id") Long id) {
        try {
            iProjetService.deleteProjet(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method deleteProjet :: " + e.toString());
        }
    }

}