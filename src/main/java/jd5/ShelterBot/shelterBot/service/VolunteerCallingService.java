package jd5.ShelterBot.shelterBot.service;

import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.UserType;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;

import java.util.List;
/**
 * Сервис вызова волонтёра
 */
public interface VolunteerCallingService {

    List<VolunteerCalling> findAllCalls(); // получить список всех вызовов

    List<VolunteerCalling> findNewCalls(); // найти новые вызовы

    List<VolunteerCalling> findNewCalls(ShelterType type); //найти новые вызовы по определённому типу пользователя

    VolunteerCalling saveCall(ParentUser parent); // добавить новый вызов

    VolunteerCalling saveCall(UserMessage userMessage);

}
