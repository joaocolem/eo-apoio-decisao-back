package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.domain.Feedback;
import com.ogrupo.eventsmicroservice.dtos.FeedbackRequestDTO;
import com.ogrupo.eventsmicroservice.repositories.FeedbackRepository;
import com.ogrupo.eventsmicroservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EventRepository eventRepository;

    public Feedback createFeedback(FeedbackRequestDTO feedbackRequestDTO) {
        Optional<Event> event = eventRepository.findById(feedbackRequestDTO.eventId());
        if (event.isEmpty()) {
            throw new RuntimeException("Event not found with id: " + feedbackRequestDTO.eventId());
        }

        Feedback feedback = new Feedback();
        feedback.setEvent(event.get());
        feedback.setParticipantEmail(feedbackRequestDTO.participantEmail());
        feedback.setRating(feedbackRequestDTO.rating());
        feedback.setComments(feedbackRequestDTO.comments());

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbacksByEvent(String eventId) {
        return feedbackRepository.findByEventId(eventId);
    }

    public List<Feedback> getFeedbacksByParticipant(String email) {
        return feedbackRepository.findByParticipantEmail(email);
    }

    public double getAverageRating(String eventId) {
        return feedbackRepository.findAverageRatingByEventId(eventId);
    }
}
