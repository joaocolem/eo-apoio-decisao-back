package com.ogrupo.eventsmicroservice.interfaces;

import com.ogrupo.eventsmicroservice.domain.Feedback;

public interface FeedbackRepositoryInterface {
    int saveFeedback(Feedback feedback, Long eventId);
}
