package com.ogrupo.eventsmicroservice.dtos;

public record UpcomingEventDTO(
        String date,
        String title,
        String description,
        String organizerName,
        int maxParticipants,
        int registeredParticipants
){}