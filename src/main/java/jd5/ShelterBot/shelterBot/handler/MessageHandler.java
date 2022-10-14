package jd5.ShelterBot.shelterBot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import jd5.ShelterBot.shelterBot.constants.BotMessageEnum;
import jd5.ShelterBot.shelterBot.constants.ButtonNameEnum;
import jd5.ShelterBot.shelterBot.keyboards.ReplyKeyboardMaker;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageHandler {

    private final ReplyKeyboardMaker replyKeyboardMaker;


    public MessageHandler(ReplyKeyboardMaker replyKeyboardMaker) {
        this.replyKeyboardMaker = replyKeyboardMaker;
    }

    public ReplyKeyboardMaker getReplyKeyboardMaker() {
        return replyKeyboardMaker;
    }

    public SendMessage answerMessage(Message message) {
        String chatId = message.chat().id().toString();
        String inputText = message.text();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId, message);
        } else if (inputText.equals(ButtonNameEnum.TELL_ABOUT_SHELTER.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.TELL_ABOUT_SHELTER.getMessage());
        } else if (inputText.equals(ButtonNameEnum.SHELTER_SCHEDULE_ADDRESS.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.SHELTER_SCHEDULE_ADDRESS.getMessage());
        } else if (inputText.equals(ButtonNameEnum.RECOMMENDATIONS_ON_SAFETY.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.RECOMMENDATIONS_ON_SAFETY.getMessage());
        } else if (inputText.equals(ButtonNameEnum.GET_AND_SAVE_CONTACT_INFO.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.GET_AND_SAVE_CONTACT_INFO.getMessage());
        } else if (inputText.equals(ButtonNameEnum.BACK.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.BACK.getMessage());
        } else {
            return new SendMessage(chatId, BotMessageEnum.GET_AND_SAVE_CONTACT_INFO.getMessage());
        }

    }

    private SendMessage getStartMessage(String chatId, Message message) {
        String firstName = message.from().firstName();
        String lastName = message.from().lastName();
        String greetings = "Здравствуй, " + lastName + " " + firstName + "!";
        SendMessage sendMessage = new SendMessage(chatId, greetings + "\n" + BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.replyMarkup(replyKeyboardMaker.getMainMenuKeyboard(0));
        return sendMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageHandler that = (MessageHandler) o;
        return replyKeyboardMaker.equals(that.replyKeyboardMaker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyKeyboardMaker);
    }
}
