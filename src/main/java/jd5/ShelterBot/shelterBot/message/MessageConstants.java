package jd5.ShelterBot.shelterBot.message;

/**
 * В этом классе хранится весь текст,
 * который используется в коде программы (являются командами)
 */
public class MessageConstants {
	
	public static final String BOT_START = "/start"; // БД WelcomeMessage
	
	public static final String MAIN_MENU = "Главное меню"; //БД MainMenuMessage
	public static final String USER_CONTACTS = "/contact"; //БД TakeContactMessage
	public static final String UNKNOWN_MESSAGE = "/unknown"; //БД UnknownMessage
	public static final String NEWUSER_UNKNOWN_MESSAGE = "/newuser_unknown"; // БД NewUserMessage

	public static final String VOLUNTEER_MESSAGE = "Сообщение от волонтера: "; //ReportServiceImpl

	// Новый пользователь
	public static final String CAT_SHELTER = "Приют для кошек"; //БД ChosenMessage
	public static final String DOG_SHELTER = "Приют для собак"; //БД ChosenMessage

	//Главное меню
	public static final String SHELTER_INFO = "Узнать информацию о приюте"; //БД ShelterInfoMessage
	public static final String HOW_TO_ADOPT = "Как взять животное из приюта"; //БД HowToAdoptMessage
	public static final String SEND_REPORT = "Прислать отчет о питомце"; //БД ReportMessage
	public static final String CALL_VOLUNTEER = "Позвать волонтера"; //Telegram API message (sendContact)
	public static final String SEND_CONTACTS = "Оставить контакты для связи"; //Telegram API message (sendContact)


	// Меню информации о приюте
	public static final String SHELTER_DESCRIPTION = "Описание приюта";
	public static final String SHELTER_ADDRESS = "Расписание работы приюта и адрес, схему проезда";
	public static final String SHELTER_RECOMMENDS = "Общие рекомендации о технике безопасности на территории приюта";
	public static final String SHELTER_SUCURITY_CONTACTS = "Контактные данные охраны для оформления пропуска на машину";

	// Меню для усыновления питомца
	public static final String DATING_RULES = "Правила знакомства с животным";
	public static final String REQUIRED_DOCUMENTS = "Cписок документов, необходимых для того, чтобы взять животное из приюта";
	public static final String TRANSPORTING_RULLES = "Рекомендаций по транспортировке";
	public static final String IF_YOUNG_PET = "Список рекомендаций по обустройству дома для молодого животного";
	public static final String IF_ADULT_PET = "Список рекомендаций по обустройству дома для взрослого животного";
	public static final String IF_HANDICAPPED_PET = "Список рекомендаций по обустройству дома для животного с ограниченными возможностями";
	public static final String REASONS_FOR_REFUSAL = "Cписок причин отказа в заборе животного из приюта";

	// Для усыновления собаки
	public static final String KENOLOGIST_RECOMMENDS = "Рекомендации по проверенным кинологам для дальнейшего обращения к ним";
	public static final String KENOLOGIST_ADVICE = "Советы кинолога по первичному общению с собакой";

}
