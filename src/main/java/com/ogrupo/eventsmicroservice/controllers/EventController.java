package com.ogrupo.eventsmicroservice.controllers;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.dtos.EventFeedbackSummaryDTO;
import com.ogrupo.eventsmicroservice.dtos.UpcomingEventDTO;
import com.ogrupo.eventsmicroservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/upcoming")
    public List<UpcomingEventDTO> getUpcomingEvents() {
        return eventService.getUpcomingEvents(); 
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable String id) {
        return eventService.findById(Long.parseLong(id));
    }

    @PostMapping
    public int createEvent(@RequestBody Event event) {
        return eventService.save(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id) {
        eventService.deleteById(Long.parseLong(id));
}

    @GetMapping("/feedback-averages")
    public List<EventFeedbackSummaryDTO> getEventFeedbackSummary() {
        return eventService.getEventFeedbackSummary();
    }
}