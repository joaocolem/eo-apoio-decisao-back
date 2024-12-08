package com.ogrupo.eventsmicroservice.dtos;

public record FeedbackRequestDTO(
    String eventId,
    String participantEmail,
    int rating, 
    String comments
) {}
