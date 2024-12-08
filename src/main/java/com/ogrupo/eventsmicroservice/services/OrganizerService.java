package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;
import com.ogrupo.eventsmicroservice.repositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    public Organizer createOrganizer(OrganizerRequestDTO organizerRequest) {
        Organizer organizer = new Organizer(organizerRequest);
        return organizerRepository.save(organizer);
    }

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public List<OrganizerRatingDTO> getOrganizersWithAverageRatings() {
        List<Object[]> results = organizerRepository.findAverageRatingsForAllOrganizers();
        List<OrganizerRatingDTO> organizerRatings = new ArrayList<>();

        for (Object[] result : results) {
            String id = (String) result[0];
            String name = (String) result[1];
            Double averageRating = result[2] != null ? ((Number) result[2]).doubleValue() : 0.0;

            organizerRatings.add(new OrganizerRatingDTO(id, name, averageRating));
        }
        return organizerRatings;
    }

}
