package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.model.BotResponse;
import jd5.ShelterBot.shelterBot.repository.BotResponseRepository;
import jd5.ShelterBot.shelterBot.service.BotResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BotResponseServiceImpl implements BotResponseService {

    private final Logger logger = LoggerFactory.getLogger(BotResponseServiceImpl.class);

    private final BotResponseRepository shelterRepository;

    public BotResponseServiceImpl(BotResponseRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

   public String getResponseMessage(String message) {
        logger.info("Trying to get response by key \"" + message + "\"");

        BotResponse response = shelterRepository.findBotResponseByKeyMessage(message);
        if(response == null) {
            logger.info("The response message is not found.");
            return null;
        }

        logger.info("The response message is found.");
        return response.getResponseMessage();
    }

    public BotResponse saveResponseMessage(String keyMessage, String responseMessage) {
        logger.info("Save key " + keyMessage + " with response " + responseMessage);

        return shelterRepository.save(new BotResponse(keyMessage, responseMessage));
    }
}
