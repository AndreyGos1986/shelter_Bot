package jd5.ShelterBot.shelterBot.service;

import jd5.ShelterBot.shelterBot.model.CallVolunteer;

public interface VolunteerService {

    CallVolunteer readCallVolunteer(Long id);

    CallVolunteer createCallVolunteer(CallVolunteer callVolunteer);

}
