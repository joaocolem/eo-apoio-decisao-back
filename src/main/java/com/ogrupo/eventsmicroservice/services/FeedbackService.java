package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.domain.Feedback;
import com.ogrupo.eventsmicroservice.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public int saveFeedback(Feedback feedback, Event event) {
        return feedbackRepository.saveFeedback(feedback, event.getId());
    }

    public List<Feedback> findFeedbacksWithMinRating(int minRating) {
        return feedbackRepository.findFeedbacksWithMinRating(minRating);
    }
}