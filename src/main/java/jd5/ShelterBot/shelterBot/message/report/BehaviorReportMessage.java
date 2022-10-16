package jd5.ShelterBot.shelterBot.message.report;

import jd5.ShelterBot.shelterBot.handler.UserMessage;

import java.time.LocalDate;

public class BehaviorReportMessage extends ReportMessage {

    @Override
    public String getMessageText() {
        return getMessageService().getResponseMessage("/report_behavior");
//        return "Пожалуйста, пришлите данные об изменениях в поступках животного. Не менее 10 символов";
    }

    @Override
    public boolean processReport(UserMessage userMessage) {
        if(userMessage.getMessage() == null || userMessage.getMessage().length() < 10) {
            return false;
        }

        service.addBehavior(userMessage.getMessage(), userMessage.getUserTelegramId(), LocalDate.now());
        return true;
    }

}