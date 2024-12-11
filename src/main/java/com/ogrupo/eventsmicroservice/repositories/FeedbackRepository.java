package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveFeedback(Feedback feedback, Long eventId) {
        String sql = "INSERT INTO feedbacks (rating, comment, event_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, feedback.getRating(), feedback.getComment(), eventId);
    }

    public List<Feedback> findFeedbacksWithMinRating(int minRating) {
        String sql = "SELECT * FROM feedbacks WHERE rating > ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, minRating), new BeanPropertyRowMapper<>(Feedback.class));
    }
}