package com.ogrupo.eventsmicroservice.domain;

import java.util.List;

import com.ogrupo.eventsmicroservice.dtos.EventRequestDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "event")
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int maxParticipants;

    private int registeredParticipants;

    private String date;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;

    public Event(EventRequestDTO eventRequest, Organizer organizer) {
        this.date = eventRequest.date();
        this.maxParticipants = eventRequest.maxParticipants();
        this.registeredParticipants = eventRequest.registeredParticipants();
        this.title = eventRequest.title();
        this.description = eventRequest.description();
        this.organizer = organizer;
    }
}
