package com.ogrupo.eventsmicroservice.interfaces;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;

import java.util.List;

public interface OrganizerRepositoryInterface {
    List<Organizer> findAll();

    int save(Organizer organizer);

    List<OrganizerRatingDTO> findAverageRatingsForAllOrganizers();
}
