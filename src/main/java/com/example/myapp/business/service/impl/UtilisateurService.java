package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import com.example.myapp.persistence.repository.EmployeeRepository;
import com.example.myapp.persistence.repository.ProfilRepository;
import com.example.myapp.persistence.repository.UtilisateurRepository;
import com.example.myapp.presentation.Utils.EmailUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import java.util.*;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfilRepository profilRepository;



    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Utilisateur> getListUtilisateur(){
        try {
            log.info("Fetching all users ");
            return utilisateurRepository.findAll();
        } catch (Exception e) {
            throw  new IllegalStateException("Error UtilisateurService in method getListUtilisateur " + e.toString());
        }
    }
    @Override
    public Utilisateur getUtilisateurById(Long id) {
        try {
            if(id == null)
                return new Utilisateur();
            Utilisateur p = utilisateurRepository.findUtilisateurById(id);
            if ( p == null )
                return new Utilisateur();
            log.info("Fetching user with id :{} ",id);
            return p;
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method getUtilisateurById :: " + e.toString());
        }
    }
    @Override
    public Utilisateur getUtilisateurByuserName(String userName) {
        try {
            if(userName == null)
                return new Utilisateur();
            Utilisateur p = utilisateurRepository.findUtilisateurByuserName(userName);
            if ( p == null )
                return new Utilisateur();
            log.info("Fetching user with username :{} ",userName);
            return p;
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method getUtilisateurByuserName :: " + e.toString());
        }
    }





    @Override
    public Utilisateur addUtilisateur(Utilisateur obj) {
        try {
            Profil role = profilRepository.findProfilByName("USER");
            Set<Profil> userRoles = new HashSet<>();
            userRoles.add(role);
            Utilisateur objNomUnique = utilisateurRepository.findUtilisateurByuserName(obj.getUserName());
            if (objNomUnique != null)
                throw new IllegalStateException("Utilisateur login token");
            obj.setCreationdate(new Timestamp(new Date().getTime()));
            obj.setUserPassword(getEncodedPassword(obj.getUserPassword()));
            //obj.setUserPassword(obj.getUserPassword());
            obj.setProfils(userRoles);
            log.info("Saving new user {} to the databse ",obj.getUserName());

            return utilisateurRepository.save(obj);
        } catch (Exception e) {
            throw  new IllegalStateException("Error UtilisateurService in method addUtilisateur " + e.toString());
        }
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur,Long id) {
        try {
            Utilisateur obj = utilisateurRepository.findUtilisateurById(id);
           obj.setUserName(utilisateur.getUserName());
           obj.setUserPassword(getEncodedPassword(utilisateur.getUserPassword()));
           obj.setEmail(utilisateur.getEmail());
           obj.setUpdatedate(new Timestamp(new Date().getTime()));
           obj.setId(id);
            log.info("updating user {} to the database ",utilisateur.getUserName());
            return utilisateurRepository.save(obj);
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method updateUtilisateur : " + e.toString());
        }

    }
    @Override
    public void deleteUtilisateur(Long id) {
        try {
            log.info("Deleting user with id {}  ",id);
            utilisateurRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method deleteUtilisateur : " + e.toString());
        }
    }

    @Override
    public Utilisateur findUtilisateurByEmail(final String email){
        log.info("Fetching user with email :{} ",email);
        return utilisateurRepository.findUtilisateurByEmail(email);
    }



    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            Utilisateur user = utilisateurRepository.findUtilisateurByuserName(currentUserName());
            if(!user.equals(null)){
               if(passwordEncoder.matches(requestMap.get("oldPassword"), user.getUserPassword())) {
                   //if(user.getUserPassword().equals(requestMap.get("oldPassword"))){
                   if (!Objects.equals(requestMap.get("oldPassword"), requestMap.get("newPassword"))) {
                       user.setUserPassword(getEncodedPassword(requestMap.get("newPassword")));
                       utilisateurRepository.save(user);
                       log.info("User with username :{} change his password",user.getUserName());
                       return new ResponseEntity<>(HttpStatus.OK);
                   }
               }
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

      public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String currentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        log.info("Get the current user username");
        return username;
    }


    //Reset Password

    @Override
    public void updateResetPasswordToken(String token, String email) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email);
        if (utilisateur != null) {
            utilisateur.setResetPasswordToken(token);
            utilisateurRepository.save(utilisateur);
        } else {
            throw new IllegalStateException("Could not find any user with the email " + email);
        }
    }
    @Override
    public Utilisateur getByResetPasswordToken(String token) {
        return utilisateurRepository.findByResetPasswordToken(token);
    }
    @Override
    public void updatePassword(Utilisateur utilisateur, String newPassword) {
        String encodedPassword=getEncodedPassword(newPassword);
        utilisateur.setUserPassword(encodedPassword);

        utilisateur.setResetPasswordToken(null);
        log.info("User with username :{} update his password",utilisateur.getUserName());
        utilisateurRepository.save(utilisateur);
    }

}


