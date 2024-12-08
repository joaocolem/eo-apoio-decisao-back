package com.ogrupo.eventsmicroservice.domain;

import java.util.List;

import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "organizer")
@Table(name = "organizer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    public Organizer(OrganizerRequestDTO organizerRequest) {
        this.name = organizerRequest.name();
        this.email = organizerRequest.email();
        this.phoneNumber = organizerRequest.phoneNumber();
    }
}
