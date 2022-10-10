package jd5.ShelterBot.shelterBot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class ShelterController implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(ShelterController.class);
    private final TelegramBot telegramBot;

    public ShelterController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            // Проверка пользователя по базе, если нет в базе, то:
            greetings(update);

            // Иначе проверка запроса и выдача соответствующего ответа (пока заглушка)
            Long chatId = update.message().chat().id();
            String message = "Сообщение-заглушка";
            sendMessage(chatId, message);

            // Получение фотографии от пользователя (не сжатое), как ее сохранить в базу надо будет подумать,
            // сам файл лежит на сервере телеги по адресу https://api.telegram.org/file/bot<token>/<file_path>

//            String photo = update.message().photo()[3].fileId();



        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void greetings(Update update) {
        String username = update.message().from().username();
        String greetings = "Привет " + username;

        Long chatId = update.message().chat().id();

        sendMessage(chatId, greetings);
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage greetingsMessage = new SendMessage(chatId, message);
        SendResponse response = telegramBot.execute(greetingsMessage);
        response.isOk();
    }
}