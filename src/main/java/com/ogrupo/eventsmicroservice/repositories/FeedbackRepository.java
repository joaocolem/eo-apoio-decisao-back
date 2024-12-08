package com.ogrupo.eventsmicroservice.repositories;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ogrupo.eventsmicroservice.domain.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByEventId(String eventId);

    List<Feedback> findByParticipantEmail(String email);

    @Query(value = "SELECT AVG(f.rating) FROM feedback f WHERE f.event_id = :eventId", nativeQuery = true)
    double findAverageRatingByEventId(@Param("eventId") String eventId);

    @Nonnull
    Optional<Feedback> findById(@Nonnull Long id);
}
