package com.ogrupo.eventsmicroservice.controllers;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;
import com.ogrupo.eventsmicroservice.services.OrganizerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody OrganizerRequestDTO organizerRequestDTO) {
        Organizer createdOrganizer = organizerService.createOrganizer(organizerRequestDTO);
        return ResponseEntity.ok(createdOrganizer);
    }

    @GetMapping("/ratings")
    public List<OrganizerRatingDTO> getOrganizersWithAverageRatings() {
        return organizerService.getOrganizersWithAverageRatings();
    }
}