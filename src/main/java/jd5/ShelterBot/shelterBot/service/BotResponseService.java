package jd5.ShelterBot.shelterBot.service;


import jd5.ShelterBot.shelterBot.model.BotResponse;

public interface BotResponseService {

    String getResponseMessage(String message);

    BotResponse saveResponseMessage(String keyMessage, String responseMessage);
}
