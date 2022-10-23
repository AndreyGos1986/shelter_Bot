package jd5.ShelterBot.shelterBot.service;

import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;

import java.util.List;

public interface VolunteerCallingService {


    List<VolunteerCalling> findAllCalls();

    List<VolunteerCalling> findNewCalls();

    List<VolunteerCalling> findNewCalls(ShelterType type);

    VolunteerCalling saveCall(ParentUser parent);

    VolunteerCalling saveCall(UserMessage userMessage);

}
