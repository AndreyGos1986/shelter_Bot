package jd5.ShelterBot.shelterBot.message.report;


import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.message.AbstractMessage;
import jd5.ShelterBot.shelterBot.service.ReportService;

public class ReportMessage extends AbstractMessage {

	protected ReportService service;

	public ReportService getService() {
		return service;
	}

	public void setService(ReportService service) {
		this.service = service;
	}

	@Override
	public String getMessageText() {
		return getMessageService().getResponseMessage("/report_start");
	}

	@Override
	public boolean send(UserMessage userMessage) {
		if(!service.isReportingAllowed(userMessage.getUserTelegramId())) {
			return true;
		}

		return super.send(userMessage);
	}

	public boolean processReport(UserMessage userMessage) {
		return true;
	}

}
