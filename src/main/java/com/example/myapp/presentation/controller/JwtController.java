package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.JWT.JwtService;
import com.example.myapp.business.service.impl.Initialize;
import com.example.myapp.persistence.JWT.JwtRequest;
import com.example.myapp.persistence.JWT.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Initialize initialise;

    @PostConstruct
    public void initRoleAndUser() {
        initialise.initRoleAndUser();
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
