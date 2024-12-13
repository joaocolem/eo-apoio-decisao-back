package com.ogrupo.eventsmicroservice.services;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;
import com.ogrupo.eventsmicroservice.repositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    // Busca todos os organizadores
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public void createOrganizer(OrganizerRequestDTO organizerRequestDTO) {
        Organizer organizer = new Organizer();
        organizer.setName(organizerRequestDTO.name());  // Acessando os campos do record diretamente
        organizer.setEmail(organizerRequestDTO.email());
        organizer.setPhoneNumber(organizerRequestDTO.phoneNumber());
        organizerRepository.save(organizer);
    }

    public List<OrganizerRatingDTO> findAverageRatingsForAllOrganizers() {
        // Não precisa de mapeamento adicional, pois já foi feito no repositório
        return organizerRepository.findAverageRatingsForAllOrganizers();
    }


}
