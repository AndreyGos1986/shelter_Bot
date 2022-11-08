package jd5.ShelterBot.shelterBot.service.impl;


import com.pengrad.telegrambot.model.Contact;
import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.model.*;
import jd5.ShelterBot.shelterBot.repository.VolunteerCallingRepository;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VolunteerCallingServiceImpl implements VolunteerCallingService {

    private final VolunteerCallingRepository volunteerCallingRepository;
    private final UserService userService;

    public VolunteerCallingServiceImpl(VolunteerCallingRepository volunteerCallingRepository, UserService userService) {
        this.volunteerCallingRepository = volunteerCallingRepository;
        this.userService = userService;
    }

    /**
     *  Метод вывода всех вызовов волонтёра, осуществлящий поиск всех вызовов через метод репозитория и
     * @return возвращающий список всех вызовов
     */
    @Override
    public List<VolunteerCalling> findAllCalls() {
        return volunteerCallingRepository.findAll();
    }
    /**
     *  Метод вывода всех новых вызовов волонтёра, осуществлящий поиск всех новых вызовов через метод репозитория и
     * @return возвращающий список всех новых вызовов
     */
    @Override
    public List<VolunteerCalling> findNewCalls() {
        return volunteerCallingRepository.findAllByStatusEquals(ReportStatus.NEW);
    }

    /**
     *  Метод поиска новых вызовой по типу пользователя, принимающий в параметры
     * @param type тип пользователя, после его осуществляется поиск всех новых вызовов через метод репозитория
     *             далее осуществляется исключение из списка тех вызовов, которые не соответствуют указанному типу,
     *             после чего
     * @return возвращается список новых вызовов по указанному типу пользователя
     */
    @Override
    public List<VolunteerCalling> findNewCalls(ShelterType type) {
        List<VolunteerCalling> list = volunteerCallingRepository.findAllByStatusEquals(ReportStatus.NEW);
        list.removeIf(a -> a.getType() != type);
        return list;
    }

    /**
     * Метод добавления вызова, принимающий в параметры
     * @param parent усыновителя, после чего осуществляется поиск по идентификатору,
     *              создаётся экземляр вызова с указанными в методе присваиваниями, после чего
     * @return в репозитории сохраняется вызов
     */
    @Override
    public VolunteerCalling saveCall(ParentUser parent) {
        ShelterUser user = userService.findUserById(parent.getShelterUserId());

        VolunteerCalling call = new VolunteerCalling();
        call.setDate(LocalDate.now());
        call.setType(user.getType());
        call.setUserId(user.getId());
        call.setFirstName(user.getFirstName());
        call.setLastName(user.getLastName());
        call.setParentId(parent.getId());
        call.setCause("Не отправлял отчет более трех дней!");
        call.setPhoneNumber(parent.getPhoneNumber());

        return volunteerCallingRepository.save(call);
    }

    /**
     * Метод добавления вызова, принимающий в параметры
     * @param userMessage сообщение от пользователя, если у сообщения нет контакных данных, возвращается null
     *                    либо из сообщения извлекаются контактные данные,
     *                    создаётся экземляр вызова волонтёра,
     *                    создаётся экземпляр класса пользователя через сервисный метод поиска по идентификатору.
     *                    Если пользователь  - null, он становится новым экземляром класса пользователя,
     *                    которму присваивается идентификатор, тип пользователься из перечисления.
     *                    Далее вызову присваются дата, тип пользователя и иные указанные параметры
     *                    После всего создаётся экземпляр усыновителя, который инициализируется через сервисный метод
     *                    поиска усыновителя по идентификатору. Если усыновитель найден, его идентификатор присваивается
     *                    вызове через метод присваивания, далее присваивается причина вызова и
     *
     *
     * @return через метод репозитория сохраняется вызов волонтёра через сообщения от пользователя
     */
    @Override
    public VolunteerCalling saveCall(UserMessage userMessage) {
        if(userMessage.getContact() == null) {
            return null;
        }

        Contact contact = userMessage.getContact();
        VolunteerCalling call = new VolunteerCalling();
        ShelterUser user = userService.findUserByTelegramId(userMessage.getUserId());
        if(user == null) {
            user = new ShelterUser();
            user.setId(-1L);
            user.setType(ShelterType.NEWUSER);
        }
        call.setDate(LocalDate.now());
        call.setType(user.getType());
        call.setUserId(user.getId());
        call.setFirstName(contact.firstName());
        call.setLastName(contact.lastName());
        call.setPhoneNumber(contact.phoneNumber());
        ParentUser parent = userService.findParentByUserId(user.getId());
        if(parent != null) {
            call.setParentId(parent.getId());
        }
        call.setCause("Сообщение от пользователя");

        return volunteerCallingRepository.save(call);
    }
}
