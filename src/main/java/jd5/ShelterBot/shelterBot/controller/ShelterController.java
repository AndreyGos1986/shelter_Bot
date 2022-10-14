package jd5.ShelterBot.shelterBot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jd5.ShelterBot.shelterBot.handler.MessageHandler;
import jd5.ShelterBot.shelterBot.keyboards.ReplyKeyboardMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class ShelterController implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(ShelterController.class);
    private final TelegramBot telegramBot;
    private final ReplyKeyboardMaker replyKeyboardMaker;
    private final MessageHandler messageHandler;

    public ShelterController(TelegramBot telegramBot, ReplyKeyboardMaker replyKeyboardMaker, MessageHandler messageHandler) {
        this.telegramBot = telegramBot;
        this.replyKeyboardMaker = replyKeyboardMaker;
        this.messageHandler = messageHandler;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Long chatId = update.message().chat().id();

            telegramBot.execute(messageHandler.answerMessage(update.message())
                    .replyMarkup(replyKeyboardMaker.getMainMenuKeyboard(0)));

            // Получение фотографии от пользователя (не сжатое), как ее сохранить в базу надо будет подумать,
            // сам файл лежит на сервере телеги по адресу https://api.telegram.org/file/bot<token>/<file_path>

//            String photo = update.message().photo()[3].fileId();

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage greetingsMessage = new SendMessage(chatId, message);
        SendResponse response = telegramBot.execute(greetingsMessage);
        response.isOk();
    }
}