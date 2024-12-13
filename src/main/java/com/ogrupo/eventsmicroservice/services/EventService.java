package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.dtos.EventFeedbackSummaryDTO;
import com.ogrupo.eventsmicroservice.dtos.UpcomingEventDTO;
import com.ogrupo.eventsmicroservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<UpcomingEventDTO> getUpcomingEvents() {
        return eventRepository.getUpcomingEvents(); // Chama o repositório que retorna os eventos futuros
    }

    public List<EventFeedbackSummaryDTO> getEventFeedbackSummary() {
        return eventRepository.getEventFeedbackSummary();
    }

    public Event findById(Long id) {
        return eventRepository.findById(id);
    }

    public int save(Event event) {
        return eventRepository.save(event);
    }

    public int deleteById(Long id) {
        return eventRepository.deleteById(id);
    }

}