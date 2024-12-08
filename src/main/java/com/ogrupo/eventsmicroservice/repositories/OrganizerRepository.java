package com.ogrupo.eventsmicroservice.repositories;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, String> {

    @Query(value = "SELECT o.id AS id, o.name AS name, AVG(f.rating) AS averageRating " +
            "FROM feedback f " +
            "JOIN event e ON f.event_id = e.id " +
            "JOIN organizer o ON e.organizer_id = o.id " +
            "GROUP BY o.id, o.name", nativeQuery = true)
    List<Object[]> findAverageRatingsForAllOrganizers();
}
