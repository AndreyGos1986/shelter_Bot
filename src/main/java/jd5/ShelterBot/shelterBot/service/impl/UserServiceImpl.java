package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.repository.ParentUserRepository;
import jd5.ShelterBot.shelterBot.repository.ShelterUserRepository;
import jd5.ShelterBot.shelterBot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ParentUserRepository parentUserRepository;

    private final ShelterUserRepository shelterUserRepository;

    public UserServiceImpl(ParentUserRepository parentUserRepository, ShelterUserRepository shelterUserRepository) {
        this.parentUserRepository = parentUserRepository;
        this.shelterUserRepository = shelterUserRepository;
    }


    public ParentUser findParentUserById(Long id) {
        return parentUserRepository.findById(id).get();
    }

    public ShelterUser findShelterUserById(Long id) {
        return shelterUserRepository.findById(id).get();
    }

    public ParentUser addNewParentUser(ParentUser parentUser) {
        return parentUserRepository.save(parentUser);
    }

    public ShelterUser addNewShelterUser(ShelterUser shelterUser) {
        return shelterUserRepository.save(shelterUser);
    }

    public void registrationShelterUserAsParentUser() {

    }

    public List<ParentUser> getAllParentUsers() {
        return parentUserRepository.findAll();
    }

    public Long getTelegramIdByParentUser(ParentUser parentUser) {
        return null;
    }
}
