package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.ILogAccessService;
import com.example.myapp.persistence.model.LogAccess;
import com.example.myapp.persistence.repository.LogAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class LogAccessService implements ILogAccessService {


    @Autowired
    private LogAccessRepository logAccessRepository;


    @Override
    public void saveLogAccess(String codeAccess, String username) {
        try {
            LogAccess logAccess = new LogAccess();
            logAccess.setCodeAccess(codeAccess);
            logAccess.setDateAuth(new Timestamp(new Date().getTime()));
            logAccess.setUsername(username);
            logAccessRepository.save(logAccess);
        } catch (Exception e) {
            throw new IllegalStateException("Error LogAccessService in method saveLogAccess :: "+e.toString());
        }
    }

    @Override
    public List<LogAccess> getListLogAccess(){
        try {
            return logAccessRepository.findAll();
        } catch (Exception e) {
            throw  new IllegalStateException("Error LogAcessService in method getListLogAcess " + e.toString());
        }
    }

}