package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.persistence.model.Event;
import com.example.myapp.persistence.model.LogAccess;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventController {

    private final IEventService iEventService;

    @PreAuthorize("hasAnyRole('ROLE_RH','ROLE_USER','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getListEvents() {
        try {
            return iEventService.getEvents();
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getListEvents :: " + e.toString());
        }
    }
}
