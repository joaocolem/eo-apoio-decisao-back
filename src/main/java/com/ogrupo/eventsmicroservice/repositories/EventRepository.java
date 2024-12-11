package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Event> findAll() {
        String sql = "SELECT * FROM events";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class));
    }

    public Event findById(Long id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Event.class), id);
    }

    public int save(Event event) {
        String sql = "INSERT INTO events (name, location, participants) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, event.getName(), event.getLocation(), event.getParticipants());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM events WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Event> findEventsWithMinParticipants(int minParticipants) {
        String sql = "SELECT * FROM events WHERE participants > ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, minParticipants), new BeanPropertyRowMapper<>(Event.class));
    }
}