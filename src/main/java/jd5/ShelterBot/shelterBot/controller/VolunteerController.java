package jd5.ShelterBot.shelterBot.controller;

import jd5.ShelterBot.shelterBot.model.CallVolunteer;
import jd5.ShelterBot.shelterBot.service.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallVolunteer> readCallVolunteer(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(volunteerService.readCallVolunteer(id));
    }

    @PostMapping
    public ResponseEntity<CallVolunteer> createCallVolunteer(@RequestBody CallVolunteer callVolunteer) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(volunteerService.createCallVolunteer(callVolunteer));
    }
}
