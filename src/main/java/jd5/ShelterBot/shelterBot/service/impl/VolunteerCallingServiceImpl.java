package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.exception.VolunteerCallingNotFoundException;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;
import jd5.ShelterBot.shelterBot.repository.VolunteerCallingRepository;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VolunteerCallingServiceImpl implements VolunteerCallingService {

    private final VolunteerCallingRepository volunteerCallingRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(VolunteerCallingServiceImpl.class);

    public VolunteerCallingServiceImpl(VolunteerCallingRepository volunteerCallingRepository) {
        this.volunteerCallingRepository = volunteerCallingRepository;
    }

    public VolunteerCalling readVolunteerCalling(Long id) {
        LOGGER.info("Получаем волонтера с id {}:", id);
        return volunteerCallingRepository.findById(id)
                .orElseThrow(VolunteerCallingNotFoundException::new);
    }

    public VolunteerCalling createVolunteerCalling(VolunteerCalling volunteerCalling) {
        LOGGER.info("Добавляем волонтера: " + volunteerCalling);
        return volunteerCallingRepository.save(volunteerCalling);
    }
}
