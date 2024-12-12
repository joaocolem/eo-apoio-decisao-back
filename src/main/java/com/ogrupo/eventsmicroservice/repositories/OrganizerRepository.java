package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Organizer> findAll() {
        String sql = "SELECT * FROM organizers";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Organizer.class));
    }

    public int save(Organizer organizer) {
        String sql = "INSERT INTO organizers (name, email, phone_number) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, "organizer.getName()", "organizer.getEmail()", 3333);
    }

    public List<Organizer> findOrganizersWithMinRating(double minRating) {
        String sql = "SELECT * FROM organizers WHERE rating > ?";
        return jdbcTemplate.query(sql, ps -> ps.setDouble(1, minRating), new BeanPropertyRowMapper<>(Organizer.class));
    }
}