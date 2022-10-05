package jd5.ShelterBot.shelterBot.controller;

import jd5.ShelterBot.shelterBot.model.VolunteerCalling;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    private final VolunteerCallingService volunteerCallingService;

    public VolunteerController(VolunteerCallingService volunteerCallingService) {
        this.volunteerCallingService = volunteerCallingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerCalling> readCallVolunteer(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(volunteerCallingService.readVolunteerCalling(id));
    }

    @PostMapping
    public ResponseEntity<VolunteerCalling> createCallVolunteer(@RequestBody VolunteerCalling volunteerCalling) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(volunteerCallingService.createVolunteerCalling(volunteerCalling));
    }
}
