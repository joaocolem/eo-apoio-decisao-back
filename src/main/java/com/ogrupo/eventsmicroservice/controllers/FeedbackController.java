package com.ogrupo.eventsmicroservice.controllers;

import com.ogrupo.eventsmicroservice.domain.Feedback;
import com.ogrupo.eventsmicroservice.dtos.FeedbackRequestDTO;
import com.ogrupo.eventsmicroservice.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Feedback createFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO) {
        return feedbackService.createFeedback(feedbackRequestDTO);
    }

    @GetMapping("/event/{eventId}")
    public List<Feedback> getFeedbacksByEvent(@PathVariable String eventId) {
        return feedbackService.getFeedbacksByEvent(eventId);
    }

    @GetMapping("/participant/{email}")
    public List<Feedback> getFeedbacksByParticipant(@PathVariable String email) {
        return feedbackService.getFeedbacksByParticipant(email);
    }

    @GetMapping("/event/{eventId}/average-rating")
    public double getAverageRating(@PathVariable String eventId) {
        return feedbackService.getAverageRating(eventId);

    }
}
