package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Feedback;
import com.ogrupo.eventsmicroservice.interfaces.FeedbackRepositoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackRepository implements FeedbackRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveFeedback(Feedback feedback, Long eventId) {
        String sql = "INSERT INTO feedbacks (rating, comment, event_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, feedback.getRating(), feedback.getComment(), eventId);
    }
}
