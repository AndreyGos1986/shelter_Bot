package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.exception.CallVolunteerNotFoundException;
import jd5.ShelterBot.shelterBot.model.CallVolunteer;
import jd5.ShelterBot.shelterBot.repository.VolunteerRepository;
import jd5.ShelterBot.shelterBot.service.VolunteerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(VolunteerServiceImpl.class);

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public CallVolunteer readCallVolunteer(Long id) {
        LOGGER.info("Получаем волонтера с id {}:", id);
        return volunteerRepository.findById(id)
                .orElseThrow(CallVolunteerNotFoundException::new);
    }

    public CallVolunteer createCallVolunteer(CallVolunteer callVolunteer) {
        LOGGER.info("Добавляем волонтера: " + callVolunteer);
        return volunteerRepository.save(callVolunteer);
    }
}
