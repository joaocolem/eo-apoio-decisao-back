package com.ogrupo.eventsmicroservice.repositories;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ogrupo.eventsmicroservice.domain.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "SELECT * FROM event e WHERE parsedatetime(e.date, 'dd/MM/yyyy') > :currentDate", nativeQuery = true)
    List<Event> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);

    @Query(value = "SELECT e.date as date, " +
            "AVG(f.rating) as avgFeedbackRating, " +
            "COUNT(DISTINCT e.id) as eventCount, " +
            "COUNT(f.id) as feedbackCount " +
            "FROM event e " +
            "LEFT JOIN feedback f ON e.id = f.event_id " +
            "GROUP BY e.date", nativeQuery = true)
    List<Tuple> findEventsWithAverageFeedbacks();

    @Nonnull
    Optional<Event> findById(@Nonnull String id);
}