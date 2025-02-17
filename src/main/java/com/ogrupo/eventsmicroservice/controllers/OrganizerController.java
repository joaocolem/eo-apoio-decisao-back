package com.ogrupo.eventsmicroservice.controllers;

import com.ogrupo.eventsmicroservice.domain.Organizer;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRatingDTO;
import com.ogrupo.eventsmicroservice.dtos.OrganizerRequestDTO;
import com.ogrupo.eventsmicroservice.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @PostMapping
    public void createOrganizer(@RequestBody OrganizerRequestDTO organizerRequestDTO) {
        organizerService.createOrganizer(organizerRequestDTO);
    }

    @GetMapping("/ratings")
    public List<OrganizerRatingDTO> getAverageRatingsForAllOrganizers() {
        return organizerService.findAverageRatingsForAllOrganizers();
    }
}