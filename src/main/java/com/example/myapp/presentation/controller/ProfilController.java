package com.example.myapp.presentation.controller;


import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.IProfilService;
import com.example.myapp.business.service.impl.UtilisateurService;
import com.example.myapp.persistence.model.Profil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/profil")
@AllArgsConstructor
public class ProfilController {
    private final IProfilService iProfilService;
    private final ILogDataService iLogDataService;
    private final UtilisateurService utilisateurService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profil> getListProfil() {
        try {
            return iProfilService.getListProfil();
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method getListProfil :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profil> getProfilById(@PathVariable("id") Long id) {
        try {
            Profil profil = iProfilService.getProfilById(id);
            return new ResponseEntity<>(profil, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method getProfilById :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Profil addProfil(@RequestBody Profil profil) {
        try {
            return iProfilService.addProfil(profil);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method addProfil :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profil> updateProfil(@RequestBody Profil profil, @PathVariable("id") Long id) {
        try {
            Profil updateProfil = iProfilService.updateProfil(profil,id);
            return new ResponseEntity<>(updateProfil, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilController in method updateProfil :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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