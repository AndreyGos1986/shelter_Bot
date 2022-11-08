package jd5.ShelterBot.shelterBot.message;

import com.pengrad.telegrambot.request.SendMessage;
import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;

public class TakeContactMessage extends AbstractMessage {

    private VolunteerCallingService volunteerCallingService;

    @Override
    public String getMessageText() {
        return getMessageService().getResponseMessage(MessageConstants.USER_CONTACTS);
    }

    public void setCallVolunteerService(VolunteerCallingService volunteerCallingService) {
        this.volunteerCallingService = volunteerCallingService;
    }

    @Override
    public boolean send(UserMessage userMessage) {
        VolunteerCalling call = volunteerCallingService.saveCall(userMessage);
        if(call != null) {
            telegramBot.execute(new SendMessage(userMessage.getUserTelegramId(), getMessageText() + call.getId()));
            return true;
        }
        return false;
    }

}
