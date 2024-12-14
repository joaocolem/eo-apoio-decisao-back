package com.ogrupo.eventsmicroservice.domain;

import jakarta.persistence.*;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subscriberName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    // Getters and Setters
}