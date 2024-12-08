package com.ogrupo.eventsmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.dtos.EventFeedbackSummaryDTO;
import com.ogrupo.eventsmicroservice.dtos.EventRequestDTO;
import com.ogrupo.eventsmicroservice.dtos.SubscriptionRequestDTO;
import com.ogrupo.eventsmicroservice.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequestDTO event) {
        return eventService.createEvent(event);
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<String> registerParticipant(@PathVariable String eventId,
            @RequestBody SubscriptionRequestDTO subscriptionRequest) {
        eventService.registerParticipant(eventId, subscriptionRequest.participantEmail());
        return ResponseEntity.ok("Participant registered successfully");
    }

    @GetMapping("/feedback-averages")
    public List<EventFeedbackSummaryDTO> getEventFeedbackAverages() {
        return eventService.getEventFeedbackAverages();
    }
}
