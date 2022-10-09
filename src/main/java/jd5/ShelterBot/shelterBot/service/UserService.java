package jd5.ShelterBot.shelterBot.service;

import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterUser;

import java.util.List;

public interface UserService {
    ParentUser findParentUserById(Long id);

    ShelterUser findShelterUserById(Long id);

    ParentUser addNewParentUser(ParentUser parentUser);

    ShelterUser addNewShelterUser(ShelterUser shelterUser);

    void registrationShelterUserAsParentUser();

    List<ParentUser> getAllParentUsers();

    Long getTelegramIdByParentUser(ParentUser parentUser);
}
