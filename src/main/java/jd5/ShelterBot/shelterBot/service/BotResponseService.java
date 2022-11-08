package jd5.ShelterBot.shelterBot.service;


import jd5.ShelterBot.shelterBot.model.BotResponse;
/**
 * Сервис ответов бота
 */
public interface BotResponseService {

    String getResponseMessage(String message); //получить соощение в ответ

    BotResponse saveResponseMessage(String keyMessage, String responseMessage); //сохранить новое сообщение
}

