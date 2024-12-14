package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.domain.Feedback;
import com.ogrupo.eventsmicroservice.interfaces.FeedbackRepositoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepositoryInterface feedbackRepository;

    public int saveFeedback(Feedback feedback, Event event) {
        return feedbackRepository.saveFeedback(feedback, event.getId());
    }
}
