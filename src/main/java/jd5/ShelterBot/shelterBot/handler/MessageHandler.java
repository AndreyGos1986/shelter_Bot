package jd5.ShelterBot.shelterBot.handler;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import jd5.ShelterBot.shelterBot.message.MainMenuMessage;
import jd5.ShelterBot.shelterBot.message.TakeContactMessage;
import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.ReportStage;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;
import jd5.ShelterBot.shelterBot.service.BotResponseService;
import jd5.ShelterBot.shelterBot.service.ReportService;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class MessageHandler {

    private final DogMap dogMap = new DogMap();
    private final CatMap catMap = new CatMap();
    private final NewUserMap newUserMap = new NewUserMap();
    private final ReportHandler reportHandler = new ReportHandler();

    private final UserService userService;
    private final TelegramBot telegramBot;
    private final BotResponseService shelterService;
    private final ReportService reportService;
    private final VolunteerCallingService volunteerCallingService;

    private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public MessageHandler(UserService userService,
                          TelegramBot telegramBot,
                          BotResponseService shelterService,
                          ReportService reportService,
                          VolunteerCallingService volunteerCallingService) {
        this.userService = userService;
        this.telegramBot = telegramBot;
        this.shelterService = shelterService;
        this.reportService = reportService;
        this.volunteerCallingService = volunteerCallingService;
    }

    @PostConstruct
    public void init() {
        for (ResponseMessage message : ResponseMessage.values()) {
            // каждому сообщению присваиваем экземпляр бота, через которого будем отправлять ответы
            message.setBot(telegramBot);

            // каждому сообщению присваиваем сервис-ответчик, который будет брать текст ответа из БД
            message.setMessageService(shelterService);

            logger.info("Initializing message: " + message);
        }

        newUserMap.init(userService);
        dogMap.init();
        catMap.init();
        reportHandler.init(reportService);

        ((MainMenuMessage) ResponseMessage.MAIN_MENU_MESSAGE.getMessage()).setUserService(userService);
        ((TakeContactMessage) ResponseMessage.TAKE_CONTACT.getMessage()).setCallVolunteerService(volunteerCallingService);
    }

    /**
     * Обработчик сообщений
     *
     * @param userMessage сообщение пользователя, от которого пришло сообщение
     */
    public void processMessage(UserMessage userMessage) {
        ShelterUser user = userService.findUserByTelegramId(userMessage.getUserTelegramId());
        Map<String, ResponseMessage> currentShelter = getMap(user.getType());
        if (reportHandler.requireReport()) {
            ReportStage stage = reportHandler.processReport(userMessage);
            if (stage == ReportStage.CANCELED) {
                ResponseMessage.MAIN_MENU_MESSAGE.send(userMessage);
            } else if(stage == ReportStage.COMPLETE) {
                ParentUser parent = userService.findParentByTelegramId(userMessage.getUserTelegramId());
                parent.setLastReportDate(LocalDateTime.now());
                userService.saveParent(parent);
            }
        } else {
            if(currentShelter == newUserMap) {
                currentShelter.getOrDefault(userMessage.getMessage(), ResponseMessage.NEWUSER_MESSAGE).send(userMessage);
            } else {
                ResponseMessage response = currentShelter.getOrDefault(userMessage.getMessage(), ResponseMessage.UNKNOWN_MESSAGE).send(userMessage);


                if (response == ResponseMessage.SEND_REPORT_MESSAGE) {
                    if(userService.findParentByTelegramId(userMessage.getUserTelegramId()) == null) {
                        telegramBot.execute(new SendMessage(userMessage.getUserTelegramId(), "У Вас нет животного, по которому надо отчитываться"));
                    } else reportHandler.startReport();
                }
            }
        }
    }

    private Map<String, ResponseMessage> getMap(ShelterType type) {
        if (type == ShelterType.CAT_SHELTER) {
            return catMap;
        } else if (type == ShelterType.DOG_SHELTER) {
            return dogMap;
        }
        return newUserMap;
    }
}
