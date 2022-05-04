package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.persistence.model.LogAccess;
import com.example.myapp.persistence.model.LogData;
import com.example.myapp.persistence.repository.LogAccessRepository;
import com.example.myapp.persistence.repository.LogDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogDataService implements ILogDataService {

    @Autowired
    private LogDataRepository logDataRepository;


    @Override
    public void saveLogData(String username, String action) {
        try {
            LogData logData = new LogData();
            logData.setUsername(username);
            logData.setAction(action);
            logDataRepository.save(logData);
        } catch (Exception e) {
            throw new IllegalStateException("Error LogDataService in method saveLogData :: "+e.toString());
        }
    }

    @Override
    public List<LogData> getListLogData(){
        try {
            return logDataRepository.findAll();
        } catch (Exception e) {
            throw  new IllegalStateException("Error LogDataService in method getListLogData " + e.toString());
        }
    }
}
