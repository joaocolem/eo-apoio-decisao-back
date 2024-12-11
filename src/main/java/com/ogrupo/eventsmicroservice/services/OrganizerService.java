package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;
import com.ogrupo.eventsmicroservice.repositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void createOrganizer(OrganizerRequestDTO organizerRequestDTO) {
        Organizer organizer = new Organizer();
        organizer.setName(organizerRequestDTO.getName());
        organizer.setRating(organizerRequestDTO.getRating());
        organizerRepository.save(organizer);
    }

    public List<Organizer> findOrganizersWithMinRating(double minRating) {
        return organizerRepository.findOrganizersWithMinRating(minRating);
    }
}