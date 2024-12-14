package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;
import com.ogrupo.eventsmicroservice.interfaces.OrganizerRepositoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepositoryInterface organizerRepository;


    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void createOrganizer(OrganizerRequestDTO organizerRequestDTO) {
        Organizer organizer = new Organizer();
        organizer.setName(organizerRequestDTO.name()); 
        organizer.setEmail(organizerRequestDTO.email());
        organizer.setPhoneNumber(organizerRequestDTO.phoneNumber());
        organizerRepository.save(organizer);
    }

    public List<OrganizerRatingDTO> findAverageRatingsForAllOrganizers() {
        return organizerRepository.findAverageRatingsForAllOrganizers();
    }
}
