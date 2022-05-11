package com.example.myapp.business.service.impl;

import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import com.example.myapp.persistence.repository.EmployeeRepository;
import com.example.myapp.persistence.repository.ProfilRepository;
import com.example.myapp.persistence.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class Initialize {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfilRepository profilRepository;

//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


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
                "houssemhmida1212@gmail.com",
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
                "902222545",
                "webservice",
                5
        );
        Employee Houssem = new Employee(
                "Houssem",
                "Hmida",
                "RH",
                Boolean.FALSE,
                LocalDate.of(2000, 4, 2),
                "14 sfax",
                "99823490",
                "websocket",
                7
        );

        employeeRepository.saveAll(
                List.of(Yassine, Houssem)
        );


        Set<Profil> simpleUserRoles = new HashSet<>();
        simpleUserRoles.add(simpleUserRole);
        //simpleUserRoles.add(managerRole);
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
}
