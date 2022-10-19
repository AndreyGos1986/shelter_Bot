package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.model.BotResponse;
import jd5.ShelterBot.shelterBot.service.BotResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jd5.ShelterBot.shelterBot.repository.BotResponseRepository;

@Service
public class BotResponseServiceImpl implements BotResponseService {

    private final Logger logger = LoggerFactory.getLogger(BotResponseServiceImpl.class);

    private final BotResponseRepository responseRepositoryepository;

    public BotResponseServiceImpl(BotResponseRepository shelterRepository) {
        this.responseRepositoryepository = shelterRepository;
    }

    /**
     * Метод поиска ответа на вопрос, принимающий в параметры
     * @param message строку с вопросом, после чего осуществляется проверка и если ответ не найден,
     *                возвращается null, если найден
     * @return возвращается ответ
     */
    public String getResponseMessage(String message) {
        logger.info("Пробуем получить ответ на вопрос \"" + message + "\"");

        BotResponse response = responseRepositoryepository.findBotResponseByKeyMessage(message);
        if (response == null) {
            logger.info("Ответ не найден");
            return null;
        }

        logger.info("Ответ найден");
        return response.getResponseMessage();
    }

    /**
     * Метод добавления нового ответа, принимающий в параметры
     * @param keyMessage ключ-запрос и
     * @param responseMessage ответ
     * @return после чего возвращает результат, что ключ-ответ сохранены в указанном в методе репозитории
     */
    public BotResponse saveResponseMessage(String keyMessage, String responseMessage) {
        logger.info(" сохраненяем ключ-запрос " + keyMessage + " и ответ " + responseMessage);

        return responseRepositoryepository.save(new BotResponse(keyMessage, responseMessage));
    }
}