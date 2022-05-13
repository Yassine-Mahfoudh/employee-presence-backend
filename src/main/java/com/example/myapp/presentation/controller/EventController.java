package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.impl.UtilisateurService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.model.Event;
import com.example.myapp.persistence.model.LogAccess;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventController {

    private final IEventService iEventService;
    private final ILogDataService iLogDataService;
    private final UtilisateurService utilisateurService;



    @PreAuthorize("hasAnyRole('ROLE_RH','ROLE_USER','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getListEvents() {
        try {
            return iEventService.getEvents();
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getListEvents :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
        try {
           // iLogDataService.saveLogData(utilisateurService.currentUserName(),"Get Demand Num : "+id);
            Event event = iEventService.getEventById(id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getEventById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Event addEvent(@RequestBody Event event) {
        try {
         //   iLogDataService.saveLogData(utilisateurService.currentUserName(),"Add new Demand");
            return iEventService.addEvent(event);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method addEvent :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateEventById(@RequestBody Event event, @PathVariable("id") Long id) {
        try {
         //   iLogDataService.saveLogData(utilisateurService.currentUserName(),"Update Demande Num : " +demande.getId());
            Event updateEvent = iEventService.updateEventById(event,id);
            return new ResponseEntity<>(updateEvent, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method updateEventById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id) {
        try {
          //  iLogDataService.saveLogData(utilisateurService.currentUserName(),"Delete Demande Num : "+id);
            iEventService.deleteEventById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method deleteEventById :: " + e.toString());
        }
    }
}
