package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.business.service.JWT.JwtService;
import com.example.myapp.business.service.impl.Initialize;
import com.example.myapp.persistence.JWT.JwtRequest;
import com.example.myapp.persistence.JWT.JwtResponse;
import com.example.myapp.persistence.model.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
@Slf4j
@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Initialize initialize;

   /* @Autowired
    private SimpMessagingTemplate template;
*/
    @Autowired
    private IUtilisateurService iUtilisateurService;

    @PostConstruct
    public void initRoleAndUser() {
        initialize.initRoleAndUser();
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

   /* @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody Utilisateur user) {

        try {
            Utilisateur currentUser =iUtilisateurService.getUtilisateurByuserName(iUtilisateurService.currentUserName());
            Utilisateur connectedUser = iUtilisateurService.connect(currentUser);
            template.convertAndSend("/channel/login",connectedUser );
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        Utilisateur currentUser =iUtilisateurService.getUtilisateurByuserName(iUtilisateurService.currentUserName());
        Utilisateur disconnectedUser = iUtilisateurService.disconnect(currentUser);
        template.convertAndSend("/channel/login",disconnectedUser);
        log.info("logout user : "+currentUser.getUserName() );

    }
*/
}
