package service;

import model.BotResponse;

public interface BotResponseService {
    String getResponseMessage(String message);
    BotResponse saveResponseMessage(String keyMessage, String responseMessage);
}
