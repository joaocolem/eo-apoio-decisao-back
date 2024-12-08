package com.ogrupo.eventsmicroservice.dtos;

public record EventFeedbackSummaryDTO(
                String date,
                double avgFeedbackRating,
                long eventCount,
                long feedbackCount) {
}
