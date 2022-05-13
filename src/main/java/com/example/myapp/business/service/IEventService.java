package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Event;

import java.util.List;

public interface IEventService {

   public List<Event> getEvents();

   Event getEventById(Long id);

   public Event addEvent(Event event);

   Event updateEventById(Event event, Long id);

   public void deleteEventById(Long id);
}
