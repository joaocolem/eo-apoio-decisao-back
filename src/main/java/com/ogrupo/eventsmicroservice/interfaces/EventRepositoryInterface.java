package com.ogrupo.eventsmicroservice.interfaces;

import com.ogrupo.eventsmicroservice.domain.Event;
import com.ogrupo.eventsmicroservice.dtos.EventFeedbackSummaryDTO;
import com.ogrupo.eventsmicroservice.dtos.UpcomingEventDTO;

import java.util.List;

public interface EventRepositoryInterface {
    List<Event> findAll();

    Event findById(Long id);

    int save(Event event);

    int deleteById(Long id);

    List<Event> findEventsWithMinParticipants(int minParticipants);

    List<EventFeedbackSummaryDTO> getEventFeedbackSummary();

    List<UpcomingEventDTO> getUpcomingEvents();
}
