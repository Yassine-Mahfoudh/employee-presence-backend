package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/utilisateur")
@AllArgsConstructor
public class UtilisateurController {
    private final IUtilisateurService iUtilisateurService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getUtilisateurById (@PathVariable("id") Long id) {
       try{ Utilisateur utilisateur = iUtilisateurService.getUtilisateurById(id);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);}
       catch (Exception e){
           throw new IllegalStateException("Error UtilisateurController in method getUtilisateurById:" +e.toString());
       }
    }

    @GetMapping(value = "/find/name/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getUtilisateurByuserName (@PathVariable("userName") String userName) {
        try{ Utilisateur utilisateur = iUtilisateurService.getUtilisateurByuserName(userName);
            return new ResponseEntity<>(utilisateur, HttpStatus.OK);}
        catch (Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getUtilisateurByuserName:" +e.toString());
        }
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_RH')")
    @GetMapping
    public List<Utilisateur> getListUtilisateur() {
        try {
            return iUtilisateurService.getListUtilisateur();
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method getListUtilisateur:" +e.toString());
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur addUtilisateur(@RequestBody Utilisateur obj){
       try{
          return iUtilisateurService.addUtilisateur(obj);
       }catch(Exception e){
           throw new IllegalStateException("Error UtilisateurController in method addUtilisateur:" +e.toString());
       }

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUtilisateur(@PathVariable("id") Long id) {
        try{
            iUtilisateurService.deleteUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw new IllegalStateException("Error UtilisateurController in method deleteUtilisateur:" +e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur obj,Long id) {
        try {
            Utilisateur updateUtilisateur = iUtilisateurService.updateUtilisateur(obj, id);
        return new ResponseEntity<>(updateUtilisateur, HttpStatus.OK);
    }
        catch(Exception e){
        throw new IllegalStateException("Error UtilisateurController in method updateUtilisateur:" +e.toString());
        }
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        return username;
    }


    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public String forUser(){
        return" this URL is only accessible to the user";
    }
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String forAdmin(){
        return" this URL is only accessible to the admin";
    }

}
