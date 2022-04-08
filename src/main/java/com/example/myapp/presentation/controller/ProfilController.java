package com.example.myapp.presentation.controller;


import com.example.myapp.business.service.IProfilService;
import com.example.myapp.persistence.model.Profil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/profil")
@AllArgsConstructor
public class ProfilController {
    private final IProfilService iProfilService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profil> getListProfil() {
        try {
            return iProfilService.getListProfil();
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method getListProfil :: " + e.toString());
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profil> getProfilById(@PathVariable("id") Long id) {
        try {
            Profil profil = iProfilService.getProfilById(id);
            return new ResponseEntity<>(profil, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method getProfilById :: " + e.toString());
        }
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Profil addProfil(@RequestBody Profil profil) {
        try {
            return iProfilService.addProfil(profil);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method addProfil :: " + e.toString());
        }
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profil> updateProfil(@RequestBody Profil profil) {
        try {
            Profil updateProfil = iProfilService.updateProfil(profil);
            return new ResponseEntity<>(updateProfil, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method updateProfil :: " + e.toString());
        }
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProfil(@PathVariable("id") Long id) {
        try {
            iProfilService.deleteProfil(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method deleteProfil :: " + e.toString());
        }
    }

}