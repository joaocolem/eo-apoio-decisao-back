package com.ogrupo.eventsmicroservice.dtos;

public record FeedbackResponseDTO(
        Long id,
        String eventId,
        String participantEmail,
        int rating,
        String comments) {
}
