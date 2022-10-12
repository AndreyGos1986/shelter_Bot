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
    private final VolunteerCallingRepository repository;
    private final UserService userService;

    public VolunteerCallingServiceImpl(VolunteerCallingRepository repositoryepository, UserService userService) {
        this.repository = repositoryepository;
        this.userService = userService;
    }

    @Override
    public List<VolunteerCalling> findAllCalls() {
        return repository.findAll();
    }

    @Override
    public List<VolunteerCalling> findNewCalls() {
        return repository.findAllByStatusEquals(ReportStatus.NEW);
    }

    @Override
    public List<VolunteerCalling> findNewCalls(ShelterType type) {
        List<VolunteerCalling> list = repository.findAllByStatusEquals(ReportStatus.NEW);
        list.removeIf(a -> a.getShelterType() != type);
        return list;
    }

    @Override
    public VolunteerCalling saveCall(ParentUser parent) {
        ShelterUser user = userService.findUserById(parent.getShelterUserId());

        VolunteerCalling call = new VolunteerCalling();
        call.setDate(LocalDate.now());
        call.setType(user.getUserType());
        call.setShelterType(user.getType());
        call.setUserId(user.getId());
        call.setFirstName(user.getName());
        call.setLastName(user.getSurname());
        call.setParentId(parent.getId());
        call.setCause("Не отправлял отчет более трех дней!");
        call.setPhoneNumber(parent.getPhoneNumber());

        return repository.save(call);
    }

    @Override
    public VolunteerCalling saveCall(UserMessage userMessage) {

        if(userMessage.getContact()== null) {
            return null;
        }

        Contact contact = userMessage.getContact();
        VolunteerCalling call = new VolunteerCalling();
        ShelterUser user = userService.findUserByTelegramId(userMessage.getUserId());
        if(user == null) {
            user = new ShelterUser();
            user.setId(-1L);
            user.setUserType(UserType.NEW_USER);
        }
        call.setDate(LocalDate.now());
        call.setType(user.getUserType());
        call.setUserId(user.getId());
        call.setFirstName(contact.firstName());
        call.setLastName(contact.lastName());
        call.setPhoneNumber(contact.phoneNumber());
        ParentUser parent = userService.findParentByUserId(user.getId());
        if(parent != null) {
            call.setParentId(parent.getId());
        }
        call.setCause("Инициатива пользователя");

        return repository.save(call);
    }
}
