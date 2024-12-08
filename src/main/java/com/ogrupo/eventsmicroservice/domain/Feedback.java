package com.ogrupo.eventsmicroservice.domain;

import com.ogrupo.eventsmicroservice.dtos.FeedbackRequestDTO;
import com.ogrupo.eventsmicroservice.dtos.FeedbackResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "feedback")
@Table(name = "feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    private String participantEmail;

    private int rating;

    private String comments;

    public Feedback(Event event, String participantEmail, int rating, String comments) {
        this.event = event;
        this.participantEmail = participantEmail;
        this.rating = rating;
        this.comments = comments;
    }

    public Feedback(FeedbackRequestDTO dto, Event event) {
        this.event = event;
        this.participantEmail = dto.participantEmail();
        this.rating = dto.rating();
        this.comments = dto.comments();
    }

    public FeedbackResponseDTO toResponseDTO() {
        return new FeedbackResponseDTO(
                this.id,
                this.event.getId(),
                this.participantEmail,
                this.rating,
                this.comments);
    }
}
