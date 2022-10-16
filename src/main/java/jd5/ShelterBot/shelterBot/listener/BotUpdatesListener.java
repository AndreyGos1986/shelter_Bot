package jd5.ShelterBot.shelterBot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import jd5.ShelterBot.shelterBot.handler.MessageHandler;
import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.message.MessageConstants;
import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
public class BotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(BotUpdatesListener.class);

    // Инжект экземляра бота, созданного в BotConfiguration
    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private UserService userService;

    @Autowired
    private VolunteerCallingService volunteerCallingService;

    // Обработчик сообщений
    @Autowired
    private MessageHandler messageHandler;

    // Настройка в качестве листинера (обработчика сообщений) объект данного класса
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * Уведомление о необходимости предоставления отчета в 20.00 в течение 30 дней каждый день недели
     */
    @Scheduled(cron = "0 20 1/30 * 1-7")
    public void run() {
        List<ParentUser> list = userService.findAllParents();
        for(ParentUser parent : list) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime probation = parent.getEndOfProbation();

            Period probationPeriod = null;
            if(probation != null) {
                probationPeriod = Period.between(now.toLocalDate(), probation.toLocalDate());
            }

            if(probationPeriod != null && probationPeriod.getDays() > 0) {
                Period period = Period.between(parent.getLastReportDate().toLocalDate(), now.toLocalDate());
                int diff = period.getDays();

                if (diff >= 1 && (parent.getLastNoticeDateAndTime() == null || now.isAfter(parent.getLastNoticeDateAndTime()))) {
                    long telegramId = userService.findTelegramIdByParent(parent);
                    telegramBot.execute(new SendMessage(telegramId, "Напоминаем Вам об отправке отчета"));
                    if(diff >= 2) {
                        telegramBot.execute(new SendMessage(telegramId, "Вы не отправляли отчет больше 2-х дней, призываю волонтЁра!"));
                        volunteerCallingService.saveCall(parent);
                    }
                    parent.setLastNoticeDateAndTime(now.plusHours(6));
                    userService.saveParent(parent);
                }
            } else {
                if(probation != null) {
                    telegramBot.execute(new SendMessage(userService.findTelegramIdByParent(parent), "Поздравляем! Вы прошли испытательный срок."));
                    parent.setEndOfProbation(null);
                    userService.saveParent(parent);
                }
            }
        }
    }

    /**
     * Метод является обработчиком входящих сообщений
     *
     * @param updates лист приходящих сообщений
     * @return CONFIRMED_UPDATES_ALL
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            // Получаем присланное сообщение
            Message message = update.message();
            UserMessage userMessage = new UserMessage();
            if (message == null) {
                // Если пришло не сообщение, проверяем, что сообщение - нажатая кнопка inline меню

                logger.info("Message == null, trying to check callbackQuery");
                CallbackQuery query = update.callbackQuery();
                if (query != null) {
                    userMessage.setUserId(query.from().id());
                    userMessage.setMessage(query.data());
                    userMessage.setUser(query.from());
                }
            } else if (hasTextMessage(message)) {
                logger.info("The message has a text");

                userMessage.setUserId(message.chat().id());
                userMessage.setMessage(message.text());
                userMessage.setUser(message.from());
            } else if (hasContacts(message)) {
                logger.info("The message has a contact");

                userMessage.setUserId(message.chat().id());
                userMessage.setMessage(MessageConstants.USER_CONTACTS);
                userMessage.setContact(message.contact());
                userMessage.setUser(message.from());
            } else if (hasPictureMessage(message)) {
                logger.info("The message has a photo");

                userMessage.setUserId(message.chat().id());
                userMessage.setMessage(message.caption());
                userMessage.setUser(message.from());

                PhotoSize current = null;
                for(PhotoSize size : message.photo()) {
                    if(current == null || current.fileSize() < size.fileSize()) {
                        current = size;
                    }
                }

                if(current != null) {
                    GetFile pic = new GetFile(current.fileId());
                    GetFileResponse response = telegramBot.execute(pic);
                    try {
                        byte[] data = telegramBot.getFileContent(response.file());
                        userMessage.setPicture(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (userMessage.getUserTelegramId() != -1) { // если сообщение имеет текст, отправляем его в обработчик
                messageHandler.processMessage(userMessage);
            }
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private boolean hasTextMessage(Message message) {
        return message.text() != null;
    }

    private boolean hasContacts(Message message) {
        return message.contact() != null;
    }

    private boolean hasPictureMessage(Message message) {
        return message.photo() != null;
    }
}

