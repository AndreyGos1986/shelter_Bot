package jd5.ShelterBot.shelterBot.message.report;

import jd5.ShelterBot.shelterBot.handler.UserMessage;

import java.time.LocalDate;

public class PhotoReportMessage extends ReportMessage {

    @Override
    public String getMessageText() {
        return getMessageService().getResponseMessage("/report_pic");
    }

    @Override
    public boolean processReport(UserMessage userMessage) {
        if(userMessage.getPicture() == null) {
            return false;
        }

        service.addPhoto(userMessage.getPicture(), userMessage.getUserTelegramId(), LocalDate.now());
        return true;
    }
}
