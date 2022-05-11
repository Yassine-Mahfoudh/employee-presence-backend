package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.persistence.model.Event;
import com.example.myapp.persistence.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> getEvents(){
        try{
            return eventRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error EventService in method getEvents " + e.toString());
        }
    }

}
