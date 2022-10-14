package jd5.ShelterBot.shelterBot.response;


import com.pengrad.telegrambot.TelegramBot;
import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.message.*;
import jd5.ShelterBot.shelterBot.message.report.*;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.service.BotResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, который хранит в себе все используемые ботом ответы
 */
public enum ResponseMessage {
	// New user messages
		WELCOME_MESSAGE(new WelcomeMessage().setMenu(ResponseMenu.EMPTY.getKeyboard())),
		NEWUSER_MESSAGE(new RepositoryResponseMessage(MessageConstants.NEWUSER_UNKNOWN_MESSAGE).setMenu(ResponseMenu.NEWUSER.getKeyboard())),
		CAT_SHELTER_CHOSEN(new ChosenMessage(ShelterType.CATS).setMenu(ResponseMenu.MAIN.getKeyboard())),
		DOG_SHELTER_CHOSEN(new ChosenMessage(ShelterType.DOGS).setMenu(ResponseMenu.MAIN.getKeyboard())),

	// Basic messages
		MAIN_MENU_MESSAGE(new MainMenuMessage()),
		UNKNOWN_MESSAGE(new RepositoryResponseMessage(MessageConstants.UNKNOWN_MESSAGE)),
		INTERNAL_ERROR_MESSAGE(new InternalErrorMessage()),
		SEND_REPORT_MESSAGE(new ReportMessage()),
		TAKE_CONTACT(new TakeContactMessage()),

	// Report messages
		PHOTO_REPORTMESSAGE(new PhotoReportMessage()),
		RATION_REPORTMESSAGE(new RationReportMessage()),
		HEALTH_REPORTMESSAGE(new HealthReportMessage()),
		BEHAVIOR_REPORTMESSAGE(new BehaviorReportMessage()),
		COMPLETE_REPORTMESSAGE(new RepositoryResponseMessage("/отчет_завершён").setMenu(ResponseMenu.PARENT_MAIN.getKeyboard())),

	// Cat shelter messages
	CATS_SHELTER_INFO_MESSAGE(new RepositoryResponseMessage("/информация_о_приюте_для_кошек").setMenu(ResponseMenu.CAT_SHELTER_INFO.getKeyboard())),
	CAT_SHELTER_DESCRIPTION(new RepositoryResponseMessage("/описание_приюта_для_кошек")),
	CAT_SHELTER_ADDRESS(new RepositoryResponseMessage("/адрес_кошачьего_приюта")),
	CAT_SHELTER_SECURITY_CONTACTS(new RepositoryResponseMessage("/контакт_КПП_если_требуется_пропуск")),
	CAT_SHELTER_RECOMMENDS(new RepositoryResponseMessage("/рекомендации_кошачьего_приюта")),
	CAT_SHELTER_CONTACTS(new RepositoryResponseMessage("/контакты_приюта_для_кошек")),

	CATS_ADOPT_MESSAGE(new RepositoryResponseMessage("/как_взять_кошку").setMenu(ResponseMenu.HOW_TO_ADOPT_CAT.getKeyboard())),
	CATS_DATING_RULES(new RepositoryResponseMessage("/правила")),
	CATS_REQUIRED_DOCUMENTS(new RepositoryResponseMessage("/необходимые_документы")),
	CATS_TRANSPORTING_RULES(new RepositoryResponseMessage("/правила_перевозки")),
	CATS_IF_YOUNG(new RepositoryResponseMessage("/как_взять_котенка")),
	CATS_IF_ADULT(new RepositoryResponseMessage("/как_взять_кошку_в_возрасте")),
	CATS_IF_HANDICAPPED(new RepositoryResponseMessage("/как_взять_побитую_кошку")),
	CATS_REASONS_FOR_REFUSAL(new RepositoryResponseMessage("/причины отказа")),

	// Dog shelter messages
	DOGS_SHELTER_INFO_MESSAGE(new RepositoryResponseMessage("/информация_о_приюте_для_собак").setMenu(ResponseMenu.DOG_SHELTER_INFO.getKeyboard())),
	DOGS_SHELTER_DESCRIPTION(new RepositoryResponseMessage("/описание_приюта_для_собак")),
	DOGS_SHELTER_ADDRESS(new RepositoryResponseMessage("/адрес_собачьего_приюта")),
	DOGS_SHELTER_SECURITY_CONTACTS(new RepositoryResponseMessage("/контакты_приюта_для_собак")),
	DOGS_SHELTER_RECOMMENDS(new RepositoryResponseMessage("/рекомендации_собачьего_приюта")),
	DOGS_SHELTER_CONTACTS(new RepositoryResponseMessage("/контакты_приюта_для_собак")),

	DOGS_ADOPT_MESSAGE(new RepositoryResponseMessage("/как_взять_собаку").setMenu(ResponseMenu.HOW_TO_ADOPT_DOG.getKeyboard())),
	DOGS_DATING_RULES(new RepositoryResponseMessage("/правила_знакомства")),
	DOGS_REQUIRED_DOCUMENTS(new RepositoryResponseMessage("/необходимые_документы")),
	DOGS_TRANSPORTING_RULES(new RepositoryResponseMessage("/правила_перевозки")),
	DOGS_YOUNG(new RepositoryResponseMessage("/взять_щенка")),
	DOGS_ADULT(new RepositoryResponseMessage("/взять_престарелую_собаку")),
	DOGS_HANDICAPPED(new RepositoryResponseMessage("/взять_подбитую_собаку")),
	DOGS_KENOLOGIST_RECOMMENDS(new RepositoryResponseMessage("/рекомендованные_кинологи")),
	DOGS_KENOLOGIST_ADVICE(new RepositoryResponseMessage("/получить_совет_от_кинолога")),
	DOGS_REASONS_FOR_REFUSAL(new RepositoryResponseMessage("/причины_отказа")),
	;




	private final Logger logger = LoggerFactory.getLogger(ResponseMessage.class);


	private final AbstractMessage message;
	
	ResponseMessage(AbstractMessage message) {
		this.message = message;
	}
	
	/**
	 * @param telegramBot объект, необходимый для отправки ответа пользователю
	 */
	public void setBot(TelegramBot telegramBot) {
		message.setBot(telegramBot);
	}

	/**
	 * @param service сервис для обращения к базе данных, откуда берется
	 *                текст для ответа
	 */
	public void setMessageService(BotResponseService service) {
		message.setMessageService(service);
	}

	/**
	 * @param userMessage класс с информацией о принятом сообщении от пользователя
	 */
	public ResponseMessage send(UserMessage userMessage) {
		logger.info("Send " + this);
		if(!message.send(userMessage)) {
			logger.error("The message was not be sent: " + this);
			ResponseMessage.INTERNAL_ERROR_MESSAGE.send(userMessage);
			return this;
		}

		return this;
	}

	public AbstractMessage getMessage() {
		return message;
	}
}
