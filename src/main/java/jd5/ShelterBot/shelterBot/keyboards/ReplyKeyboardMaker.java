package jd5.ShelterBot.shelterBot.keyboards;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import jd5.ShelterBot.shelterBot.constants.ButtonNameEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyKeyboardMaker {

    public ReplyKeyboardMarkup getMainMenuKeyboard(int keyboardNumber) {
                ReplyKeyboardMarkup replyKeyboardMarkup1 = new ReplyKeyboardMarkup(
                new KeyboardButton[]{new KeyboardButton(ButtonNameEnum.TELL_ABOUT_SHELTER.getButtonName()),
                        new KeyboardButton(ButtonNameEnum.SHELTER_SCHEDULE_ADDRESS.getButtonName())},

                new KeyboardButton[]{new KeyboardButton(ButtonNameEnum.RECOMMENDATIONS_ON_SAFETY.getButtonName()),
                        new KeyboardButton(ButtonNameEnum.GET_AND_SAVE_CONTACT_INFO.getButtonName())},

                new KeyboardButton[]{new KeyboardButton(ButtonNameEnum.SEND_THE_REPORT.getButtonName())},

                new KeyboardButton[]{new KeyboardButton(ButtonNameEnum.BACK.getButtonName())}
                );


        List<ReplyKeyboardMarkup> keyboard = new ArrayList<>();
        keyboard.add(replyKeyboardMarkup1);


        return keyboard.get(keyboardNumber).selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false);

    }

}