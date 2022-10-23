package jd5.ShelterBot.shelterBot.handler;

import jd5.ShelterBot.shelterBot.message.report.ReportMessage;
import jd5.ShelterBot.shelterBot.model.ReportStage;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;
import jd5.ShelterBot.shelterBot.service.ReportService;

import java.util.HashMap;
import java.util.Map;

public class ReportHandler {
	
	private final Map<ReportStage, ResponseMessage> map = new HashMap<>();
	private ReportStage reportStage = ReportStage.CANCELED;
	
	public void init(ReportService reportService) {
		map.put(ReportStage.PHOTO, ResponseMessage.PHOTO_REPORTMESSAGE);
		map.put(ReportStage.RATION, ResponseMessage.RATION_REPORTMESSAGE);
		map.put(ReportStage.HEALTH, ResponseMessage.HEALTH_REPORTMESSAGE);
		map.put(ReportStage.BEHAVIOR, ResponseMessage.BEHAVIOR_REPORTMESSAGE);
		map.put(ReportStage.COMPLETE, ResponseMessage.COMPLETE_REPORTMESSAGE);
		
		for(ResponseMessage message : map.values()) {
			if(message.getMessage() instanceof ReportMessage) {
				((ReportMessage) message.getMessage()).setService(reportService);
			}
		}
		((ReportMessage) ResponseMessage.SEND_REPORT_MESSAGE.getMessage()).setService(reportService);
	}
	
	public boolean requireReport() {
		return reportStage != ReportStage.COMPLETE && reportStage != ReportStage.CANCELED;
	}
	
	public void startReport() {
		reportStage = ReportStage.PHOTO;
	}
	
	public void cancelReport() {
		reportStage = ReportStage.CANCELED;
	}
	
	public ReportStage processReport(UserMessage userMessage) {
		if(userMessage.getMessage() != null && userMessage.getMessage().equals("Отмена")) {
			cancelReport();
            return ReportStage.CANCELED;
        }
		
		ReportMessage message = (ReportMessage) map.get(reportStage).getMessage();
	    if(!message.processReport(userMessage)) {
	         message.send(userMessage);
	    } else {
	        reportStage = ReportStage.values()[reportStage.ordinal() + 1]; // next state
	        map.get(reportStage).send(userMessage);
	    }
	    
	    return reportStage;
	}
}
