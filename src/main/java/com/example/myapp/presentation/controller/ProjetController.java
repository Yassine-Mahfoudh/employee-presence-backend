package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IProjectService;
import com.example.myapp.persistence.model.Project;
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
    private final IProjectService iProjetService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Project> getListProjet() {
        try {
            return iProjetService.getListProjet();
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method getListProjet :: " + e.toString());
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProjetById(@PathVariable("id") Long id) {
        try {
            Project projet = iProjetService.getProjetById(id);
            return new ResponseEntity<>(projet, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method getProjetById :: " + e.toString());
        }
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project addProjet(@RequestBody Project projet) {
        try {
            return iProjetService.addProjet(projet);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetController in method addProjet :: " + e.toString());
        }
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> updateProjet(@RequestBody Project projet, @PathVariable("id") Long id) {
        try {
            Project updateProjet = iProjetService.updateProjet(projet, id);
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