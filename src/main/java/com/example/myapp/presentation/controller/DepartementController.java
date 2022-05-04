package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IDepartementService;
import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.impl.UtilisateurService;
import com.example.myapp.persistence.model.Departement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departement")
@AllArgsConstructor
public class DepartementController {

    private final IDepartementService iDepartementService;
    private final ILogDataService iLogDataService;
    private final UtilisateurService utilisateurService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Departement> getListDepartement() {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Get List of Department");
            return iDepartementService.getListDepartement();
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementController in method getListDepartement :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departement> getDepartementById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Get Department Num : "+id);
            Departement departement = iDepartementService.getDepartementById(id);
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementController in method getDepartementById :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RH')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Departement addDepartement(@RequestBody Departement departement) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Add New Department");

            return iDepartementService.addDepartement(departement);
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementController in method addDepartement :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RH')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departement> updateDepartementById(@RequestBody Departement departement, @PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Update Department Num : "+id);
            Departement updateDepartement = iDepartementService.updateDepartementById(departement, id);
            return new ResponseEntity<>(updateDepartement, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementController in method updateDepartementById :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RH')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDepartementById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(utilisateurService.currentUserName(),"Delete Department Num : "+id);
            iDepartementService.deleteDepartementById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementController in method deleteDepartementById :: " + e.toString());
        }
    }
}
