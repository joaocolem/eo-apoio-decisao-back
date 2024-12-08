package com.ogrupo.eventsmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.domain.Subscription;
import com.ogrupo.eventsmicroservice.dtos.EmailRequestDTO;
import com.ogrupo.eventsmicroservice.dtos.EventFeedbackSummaryDTO;
import com.ogrupo.eventsmicroservice.dtos.EventRequestDTO;
import com.ogrupo.eventsmicroservice.exceptions.EventFullException;
import com.ogrupo.eventsmicroservice.exceptions.EventNotFoundException;
import com.ogrupo.eventsmicroservice.repositories.EventRepository;
import com.ogrupo.eventsmicroservice.repositories.OrganizerRepository;
import com.ogrupo.eventsmicroservice.repositories.SubscriptionRepository;

import jakarta.persistence.Tuple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDTO eventRequest) {
        Organizer organizer = organizerRepository.findById(eventRequest.organizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found with id: " + eventRequest.organizerId()));
        Event newEvent = new Event(eventRequest, organizer);

        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event) {
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if (isEventFull(event)) {
            throw new EventFullException();
        }

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, "Confirmação de Inscrição",
                "Você foi inscrito no evento com sucesso!");
        emailServiceClient.sendEmail(emailRequest);
    }

    public List<EventFeedbackSummaryDTO> getEventFeedbackAverages() {
        List<Tuple> results = eventRepository.findEventsWithAverageFeedbacks();
        return results.stream()
                .map(result -> {
                    String date = result.get("date", String.class);
                    double avgFeedbackRating = result.get("avgFeedbackRating", Double.class);
                    long eventCount = result.get("eventCount", Long.class);
                    long feedbackCount = result.get("feedbackCount", Long.class);
                    return new EventFeedbackSummaryDTO(date, avgFeedbackRating, eventCount, feedbackCount);
                })
                .collect(Collectors.toList());

    }

}