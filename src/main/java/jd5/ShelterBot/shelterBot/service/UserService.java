package jd5.ShelterBot.shelterBot.service;

import model.ParentUser;
import model.User;
import jd5.ShelterBot.shelterBot.model.UserType;

import java.util.List;

/**
 * Общий сервис пользователей
 */
public interface UserService {

    ParentUser findParentByUserId(long id); //найти усыновителя по идентификатору

    User findUserByTelegramId(long id); // найти пользователя по идентификатору телеграмм

    List<User> findAllByType(UserType type); //вывести всех пользователей одного типа

    User findUserById(long id); // найти пользователя по идентификатору

    User saveUser(User user); // сохранить нового пользователя

    ParentUser saveParent(ParentUser parent); //сохранить усыновителя

    ParentUser registerAsParent(long userId, String phoneNumber); // регистрация пользователя в качестве
                                                                    //усыновителя

    List<ParentUser> findAllParents(); // вывести всех усыновителей

    long findTelegramIdByParent(ParentUser parent); //получить телеграмм-идентификатор усыновителя

    ParentUser findParentById(long parentId); // получить усыновителя по его идентификационному номеру

    ParentUser findParentByTelegramId(long telegramId); //получить усыновителя по телеграмм-идентификатору усыновителя

}
