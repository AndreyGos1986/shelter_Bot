package jd5.ShelterBot.shelterBot.handler;

import jd5.ShelterBot.shelterBot.message.report.ReportMessage;
import jd5.ShelterBot.shelterBot.model.StepOfReport;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;
import jd5.ShelterBot.shelterBot.service.ReportService;

import static jd5.ShelterBot.shelterBot.model.StepOfReport.*;

import java.util.HashMap;
import java.util.Map;

public class ReportHandler {

    private final Map<StepOfReport, ResponseMessage> map = new HashMap<>();
    private StepOfReport stepOfReport = CANCELED;

    public void init(ReportService reportService) {
        map.put(PHOTO, ResponseMessage.PHOTO_REPORTMESSAGE);
        map.put(RATION, ResponseMessage.RATION_REPORTMESSAGE);
        map.put(HEALTH, ResponseMessage.HEALTH_REPORTMESSAGE);
        map.put(BEHAVIOR, ResponseMessage.BEHAVIOR_REPORTMESSAGE);
        map.put(COMPLETE, ResponseMessage.COMPLETE_REPORTMESSAGE);

        for (ResponseMessage message : map.values()) {
            if (message.getMessage() instanceof ReportMessage) {
                ((ReportMessage) message.getMessage()).setService(reportService);
            }
        }
        ((ReportMessage) ResponseMessage.SEND_REPORT_MESSAGE.getMessage()).setService(reportService);
    }

    public boolean requireReport() {
        return stepOfReport != COMPLETE && stepOfReport != CANCELED;
    }

    public void startReport() {
        stepOfReport = PHOTO;
    }

    public void cancelReport() {
        stepOfReport = CANCELED;
    }

    public StepOfReport processReport(UserMessage userMessage) {
        if (userMessage.getMessage() != null && userMessage.getMessage().equals("Отмена")) {
            cancelReport();
            return CANCELED;
        }

        ReportMessage message = (ReportMessage) map.get(stepOfReport).getMessage();
        if (!message.processReport(userMessage)) {
            message.send(userMessage);
        } else {
            stepOfReport = StepOfReport.values()[stepOfReport.ordinal() + 1]; // next state
            map.get(stepOfReport).send(userMessage);
        }

        return stepOfReport;
    }
}
