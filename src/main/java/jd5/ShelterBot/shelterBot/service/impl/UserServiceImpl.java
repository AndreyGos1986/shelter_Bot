package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.model.UserType;
import jd5.ShelterBot.shelterBot.repository.ParentRepository;
import jd5.ShelterBot.shelterBot.repository.UserRepository;
import jd5.ShelterBot.shelterBot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ParentRepository parentRepository;

    public UserServiceImpl(UserRepository repository, ParentRepository parentRepository) {
        this.userRepository = repository;
        this.parentRepository = parentRepository;
    }

    @Override
    public List<ParentUser> findAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public long findTelegramIdByParent(ParentUser parent) {
        ShelterUser user = findUserById(parent.getShelterUserId());
        if(user == null) {
            return -1;
        }

        return user.getTelegramId();
    }

    @Override
    public ParentUser findParentById(long parentId) {
        Optional<ParentUser> user = parentRepository.findById(parentId);
        return user.orElse(null);

    }

    @Override
    public ParentUser findParentByTelegramId(long telegramId) {
        ShelterUser user = userRepository.findUserByTelegramId(telegramId);
        if(user == null) {
            return null;
        }

        return parentRepository.findByShelterUserId(user.getId());
    }

    @Override
    public ParentUser registerAsParent(long userId, String phoneNumber) {
        Optional<ShelterUser> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return null;
        }

        ParentUser parent = new ParentUser();
        parent.setShelterUserId(userId);
        parent.setPhoneNumber(phoneNumber);
        return parentRepository.save(parent);
    }

    @Override
    public ParentUser findParentByUserId(long id) {
        return parentRepository.findByShelterUserId(id);
    }

    @Override
    public ShelterUser findUserByTelegramId(long id) {
        ShelterUser user = userRepository.findUserByTelegramId(id);
        if(user == null) {
            user = new ShelterUser();
            user.setUserType(UserType.NEW_USER);
            user.setId(-1L);
        }

        return user;
    }

    @Override
    public List<ShelterUser> findAllByType(ShelterType type) {
        return userRepository.findAllByType(type);
    }

    @Override
    public ShelterUser findUserById(long id) {
        return userRepository.findById(id).orElseThrow(); //UserNotFoundException::new
    }

    /**
     * @param user регистрация пользователя в системе телеграм бота
     * @return возврат сохраненного в базе данных юзера
     */
    @Override
    public ShelterUser saveUser(ShelterUser user) {
        return userRepository.save(user);
    }

    @Override
    public ParentUser saveParent(ParentUser parent) {
        return parentRepository.save(parent);
    }
}
