package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return jdbcTemplate.update(sql, organizer.getName(), organizer.getEmail(), organizer.getPhoneNumber());
    }

    public List<OrganizerRatingDTO> findAverageRatingsForAllOrganizers() {
        String sql = "SELECT o.id AS id, o.name AS name, AVG(f.rating) AS averageRating " +
                "FROM feedbacks f " +
                "JOIN events e ON f.event_id = e.id " +
                "JOIN organizers o ON e.organizer_id = o.id " +
                "GROUP BY o.id, o.name";

        return jdbcTemplate.query(sql, new RowMapper<OrganizerRatingDTO>() {
            @Override
            public OrganizerRatingDTO mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new OrganizerRatingDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("averageRating")
                );
            }
        });
    }
}
