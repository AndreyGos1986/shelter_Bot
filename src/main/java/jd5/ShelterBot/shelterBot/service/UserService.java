package jd5.ShelterBot.shelterBot.service;


import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;


import java.util.List;

/**
 * Общий сервис пользователей
 */
public interface UserService {

    ParentUser findParentByUserId(long id); //найти усыновителя по идентификатору

    ShelterUser findUserByTelegramId(long id); // найти пользователя по идентификатору телеграмм

    List<ShelterUser> findAllByType(ShelterType type); //вывести всех пользователей одного типа

    ShelterUser findUserById(long id); // найти пользователя по идентификатору

    ShelterUser saveUser(ShelterUser user); // сохранить нового пользователя

    ParentUser saveParent(ParentUser parent); //сохранить усыновителя

    ParentUser registerAsParent(long userId, String phoneNumber); // регистрация пользователя в качестве
    //усыновителя

    List<ParentUser> findAllParents(); // вывести всех усыновителей

    long findTelegramIdByParent(ParentUser parent); //получить телеграмм-идентификатор усыновителя

    ParentUser findParentById(long parentId); // получить усыновителя по его идентификационному номеру

    ParentUser findParentByTelegramId(long telegramId); //получить усыновителя по телеграмм-идентификатору усыновителя

}