package service;

import model.BotResponse;

/**
 * Сервис ответов бота
 */
public interface BotResponseService {

    String getResponseMessage(String message); //получить соощение в ответ
    BotResponse saveResponseMessage(String keyMessage, String responseMessage); //сохранить новое сообщение
}
