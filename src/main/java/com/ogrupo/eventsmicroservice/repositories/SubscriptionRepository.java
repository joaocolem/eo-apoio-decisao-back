package com.ogrupo.eventsmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ogrupo.eventsmicroservice.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}