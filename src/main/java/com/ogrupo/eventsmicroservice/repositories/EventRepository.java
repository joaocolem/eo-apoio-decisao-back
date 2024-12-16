package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.dtos.EventFeedbackSummaryDTO;
import com.ogrupo.eventsmicroservice.dtos.UpcomingEventDTO;
import com.ogrupo.eventsmicroservice.interfaces.EventRepositoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository implements EventRepositoryInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Event> findAll() {
        String sql = "SELECT * FROM events";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class));
    }

    @Override
    public Event findById(Long id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Event.class), id);
    }

    @Override
    public int save(Event event) {
        String sql = "INSERT INTO events (name, location, participants) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, event.getName(), event.getLocation(), event.getParticipants());
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM events WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Event> findEventsWithMinParticipants(int minParticipants) {
        String sql = "SELECT * FROM events WHERE participants > ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, minParticipants), new BeanPropertyRowMapper<>(Event.class));
    }

    @Override
    public List<EventFeedbackSummaryDTO> getEventFeedbackSummary() {
        String sql = "SELECT e.event_date AS date, " +
                "AVG(f.rating) AS avgFeedbackRating, " +
                "COUNT(DISTINCT e.id) AS eventCount, " +
                "COUNT(f.id) AS feedbackCount " +
                "FROM events e " +
                "LEFT JOIN feedbacks f ON e.id = f.event_id " +
                "GROUP BY e.event_date";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new EventFeedbackSummaryDTO(
                rs.getString("date"),
                rs.getDouble("avgFeedbackRating"),
                rs.getLong("eventCount"),
                rs.getLong("feedbackCount")));
    }

    @Override
    public List<UpcomingEventDTO> getUpcomingEvents() {
        String sql = "SELECT e.event_date AS date, " +
                "e.name AS title, " +
                "e.description, " +
                "o.name AS organizerName, " + 
                "e.participants AS maxParticipants, " +
                "COUNT(s.id) AS registeredParticipants " +
                "FROM events e " +
                "LEFT JOIN subscriptions s ON e.id = s.event_id " +
                "LEFT JOIN organizers o ON e.organizer_id = o.id " + 
                "WHERE e.event_date > CURRENT_DATE " +
                "GROUP BY e.event_date, e.name, e.description, o.name, e.participants " +
                "ORDER BY e.event_date ASC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new UpcomingEventDTO(
                rs.getString("date"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("organizerName"), 
                rs.getInt("maxParticipants"),
                rs.getInt("registeredParticipants")));
    }
}
