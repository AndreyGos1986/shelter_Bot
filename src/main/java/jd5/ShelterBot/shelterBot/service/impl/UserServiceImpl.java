package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.UserNotFoundException;
import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
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

    /**
     * Метод выводящий список всех усыновителей
     *
     * @return возвращает список всех усыновителей
     */
    @Override
    public List<ParentUser> findAllParents() {
        return parentRepository.findAll();
    }

    /**
     * Метод полученния телеграмм идентификатора усыновителя, принимающий в параметры
     *
     * @param parent усыновителя. Инициализируется пользователь через поиск по идентификатору и если не находит, возвращает -1, если находит
     * @return возвращает телеграмм идентификатор
     */
    @Override
    public long findTelegramIdByParent(ParentUser parent) {
        ShelterUser user = findUserById(parent.getShelterUserId());
        if (user == null) {
            return -1;
        }

        return user.getTelegramId();
    }

    /**
     * Метод поиска усыновителя, принимающий  в параметры
     *
     * @param parentId идентификатор усыновителя, осуществляет поиск по идентифкатору и
     * @return возвращает усыновителя, либо возвращает Null
     */
    @Override
    public ParentUser findParentById(long parentId) {
        Optional<ParentUser> user = parentRepository.findById(parentId);
        return user.orElse(null);

    }

    /**
     * Метод поиска усыновителя, принимающий в параметры
     *
     * @param telegramId телеграмм идентификатор, осуществляет поиск по указанному идентификатору и если
     *                   ничего не нашёл, возвращает null, либо
     * @return возвращает найденного усыновителя
     */
    @Override
    public ParentUser findParentByTelegramId(long telegramId) {
        ShelterUser user = userRepository.findUserByTelegramId(telegramId);
        if (user == null) {
            return null;
        }

        return parentRepository.findByShelterUserId(user.getId());
    }

    /**
     * Метод регистрации пользователя в качестве усыновителя. Принимает в параметры
     *
     * @param userId      идент-р пользователя
     * @param phoneNumber номер телефона
     *                    Осущетсвляет поиск пользователя по идент-ру и если не находит, возвращает  Null,
     *                    если находит, создаётся новый экземпляр класса Parent которому присваиваются идентификатор и
     *                    номер телефона
     * @return после чего новый усыновитель сохраняется в репозитории
     */
    @Override
    public ParentUser registerAsParent(long userId, String phoneNumber) {
        Optional<ShelterUser> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }

        ParentUser parent = new ParentUser();
        parent.setShelterUserId(userId);
        parent.setPhoneNumber(phoneNumber);
        return parentRepository.save(parent);
    }

    /**
     * Метод поиска усыновиетля по идентификатору пользователя
     *
     * @param id идентификатор
     * @return возвращает найденного усыновителя
     */
    @Override
    public ParentUser findParentByUserId(long id) {
        return parentRepository.findByShelterUserId(id);
    }

    @Override
    public ShelterUser findUserByTelegramId(long id) {
        ShelterUser user = userRepository.findUserByTelegramId(id);
        if (user == null) {
            user = new ShelterUser();
            user.setType(ShelterType.NEWUSER);
            user.setId(-1L);
        }

        return user;
    }

    /**
     * Метод вывода всех пользователей по типу, принимающий в параметры
     * @param type тип пользователя из перечисления, после поиска через метод репозитория
     * @return возвращает список найденных по типу пользователей
     */
    @Override
    public List<ShelterUser> findAllByType(ShelterType type) {
        return userRepository.findAllByType(type);
    }

    /**
     * Метод поиска пользователя по идентификатору, принимающий в параметры
     * @param id идентификатор, осуществляющий поиск через метод репозитория и
     * @return возвращающий результат, либо исключение
     */
    @Override
    public ShelterUser findUserById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    /**
     * @param user регистрация пользователя в системе телеграм бота
     * @return возврат сохраненного в базе данных юзера
     */
    @Override
    public ShelterUser saveUser(ShelterUser user) {
        return userRepository.save(user);
    }

    /**
     *  Метод добавления усыновителя, принимающий в параметры
     * @param parent - усыновителя и через метод репозитория сохраняющий его, после чего
     * @return возвращает результат
     */
    @Override
    public ParentUser saveParent(ParentUser parent) {
        return parentRepository.save(parent);
    }

}
