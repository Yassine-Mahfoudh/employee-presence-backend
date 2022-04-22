package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Employee;
//import com.example.myapp.persistence.model.PasswordResetToken;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import com.example.myapp.persistence.repository.EmployeeRepository;
//import com.example.myapp.persistence.repository.PasswordResetTokenRepository;
import com.example.myapp.persistence.repository.ProfilRepository;
import com.example.myapp.persistence.repository.UtilisateurRepository;
import com.example.myapp.presentation.Utils.EmailUtils;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class UtilisateurService implements IUtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfilRepository profilRepository;

//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Utilisateur> getListUtilisateur(){
        try {
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
            return p;
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method getUtilisateurByuserName :: " + e.toString());
        }
    }


    @Override
    public void initRoleAndUser() {
        Profil adminRole = new Profil(
                "ADMIN"
        );
        Profil RhRole = new Profil(
                "RH"
        );
        Profil simpleUserRole = new Profil(
                "USER"
        );
        Profil managerRole = new Profil(
                "MANAGER"
        );
        profilRepository.saveAll(
                List.of(adminRole, RhRole,simpleUserRole,managerRole)
        );

        Utilisateur RhUser = new Utilisateur(
                "houssem",
                passwordEncoder.encode("0000"),
               // "0000",
                "houssem@gmail.com",
                new HashSet<Profil>()
        );
        Utilisateur adminUser = new Utilisateur(
                "yassine",
                passwordEncoder.encode("1111"),
              //  "1111",
                "ymahfoudh55@gmail.com",
                new HashSet<Profil>()
        );
        Utilisateur simpleUser = new Utilisateur(
                "oussama",
                passwordEncoder.encode("2222"),
                //"2222",
                "oussama@gmail.com",
                new HashSet<Profil>()
        );
        Utilisateur managerUser = new Utilisateur(
                "john",
                passwordEncoder.encode("3333"),
                //"3333",
                "john@gmail.com",
                new HashSet<Profil>()
        );

        Employee Yassine = new Employee(
                "Yassine",
                "Mahfoudh",
                "admin",
                Boolean.TRUE,
                LocalDate.of(2000, 6, 29),
                "68 dar chaabene",
                "902222545"
        );
        Employee Houssem = new Employee(
                "Houssem",
                "Hmida",
                "RH",
                Boolean.FALSE,
                LocalDate.of(2000, 4, 2),
                "14 sfax",
                "99823490"
        );

        employeeRepository.saveAll(
                List.of(Yassine, Houssem)
        );

        Set<Profil> simpleUserRoles = new HashSet<>();
        simpleUserRoles.add(simpleUserRole);
        simpleUser.setProfils(simpleUserRoles);

        Set<Profil> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setProfils(adminRoles);
        adminUser.setEmployee(Yassine);

        Set<Profil> RhRoles = new HashSet<>();
        RhRoles.add(RhRole);
        //RhRoles.add(simpleUserRole);
        RhUser.setProfils(RhRoles);
        RhUser.setEmployee(Houssem);


        Set<Profil> managerRoles = new HashSet<>();
        managerRoles.add(managerRole);
        managerUser.setProfils(managerRoles);

        utilisateurRepository.saveAll(
                List.of(adminUser,RhUser,simpleUser,managerUser)
        );
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
            return utilisateurRepository.save(obj);
        } catch (Exception e) {
            throw  new IllegalStateException("Error UtilisateurService in method addUtilisateur " + e.toString());
        }
    }

    @Transactional
    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur,Long id) {
        try {
            Utilisateur obj = utilisateurRepository.findUtilisateurById(id);
           obj.setUserName(utilisateur.getUserName());
           obj.setUserPassword(getEncodedPassword(utilisateur.getUserPassword()));
           obj.setEmail(utilisateur.getEmail());
           obj.setUpdatedate(new Timestamp(new Date().getTime()));
           obj.setId(id);
            return utilisateurRepository.save(obj);
        } catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method updateUtilisateur : " + e.toString());
        }

    }
    @Transactional
    @Override
    public void deleteUtilisateur(Long id) {
        try {
            utilisateurRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error UtilisateurService in method deleteUtilisateur : " + e.toString());
        }
    }

    @Override
    public Utilisateur findUtilisateurByEmail(final String email){
        return utilisateurRepository.findUtilisateurByEmail(email);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            Utilisateur user = utilisateurRepository.findUtilisateurByEmail(requestMap.get("email"));
            if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())){
                emailUtils.forgotMail(user.getEmail(),"Credentials by st2i",user.getUserName(),user.getUserPassword());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            Utilisateur user = utilisateurRepository.findUtilisateurByuserName(currentUserName());
            if(!user.equals(null)){
               if(passwordEncoder.matches(requestMap.get("oldPassword"), user.getUserPassword())){
                //if(user.getUserPassword().equals(requestMap.get("oldPassword"))){
                    user.setUserPassword(getEncodedPassword(requestMap.get("newPassword")));
                    utilisateurRepository.save(user);
                    return new ResponseEntity<>("Password Updated Successufuly",HttpStatus.OK);
                }
                return new ResponseEntity<>("Incorrect Old Password ",HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Something  goes wrong ",HttpStatus.INTERNAL_SERVER_ERROR);
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
        return username;
    }

}



