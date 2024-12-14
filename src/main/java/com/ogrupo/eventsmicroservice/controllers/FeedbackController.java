package com.ogrupo.eventsmicroservice.controllers;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.domain.Feedback;
import com.ogrupo.eventsmicroservice.services.EventService;
import com.ogrupo.eventsmicroservice.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private EventService eventService;

    @PostMapping("/{eventId}")
    public int createFeedback(@PathVariable String eventId, @RequestBody Feedback feedback) {
        Event event = eventService.findById(Long.parseLong(eventId));
        return feedbackService.saveFeedback(feedback, event);
    }
}