package jd5.ShelterBot.shelterBot.service;

import jd5.ShelterBot.shelterBot.model.VolunteerCalling;

public interface VolunteerCallingService {

    VolunteerCalling readVolunteerCalling(Long id);

    VolunteerCalling createVolunteerCalling(VolunteerCalling volunteerCalling);

}
