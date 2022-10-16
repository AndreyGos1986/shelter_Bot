package jd5.ShelterBot.shelterBot.response;


import com.pengrad.telegrambot.model.request.*;
import jd5.ShelterBot.shelterBot.message.MessageConstants;
import static jd5.ShelterBot.shelterBot.message.MessageConstants.*;
/**
 * Класс, который хранит в себе все используемые ботом меню
 */
public enum ResponseMenu {

	EMPTY(),

	MAIN(SHELTER_INFO, HOW_TO_ADOPT, CALL_VOLUNTEER),

	PARENT_MAIN(SHELTER_INFO,
			HOW_TO_ADOPT,
			SEND_REPORT,
			CALL_VOLUNTEER),

	DOG_SHELTER_INFO
			(MAIN_MENU,
			SHELTER_DESCRIPTION,
			SHELTER_ADDRESS,
			SHELTER_SUCURITY_CONTACTS,
			SHELTER_RECOMMENDS,
			SEND_CONTACTS),

	CAT_SHELTER_INFO
			(MAIN_MENU,
			SHELTER_DESCRIPTION,
			SHELTER_ADDRESS,
			SHELTER_SUCURITY_CONTACTS,
			SHELTER_RECOMMENDS,
			SEND_CONTACTS),

	HOW_TO_ADOPT_CAT
			(MAIN_MENU,
			DATING_RULES,
			REQUIRED_DOCUMENTS,
			TRANSPORTING_RULLES,
			IF_YOUNG_PET,
			IF_ADULT_PET,
			IF_HANDICAPPED_PET,
			REASONS_FOR_REFUSAL,
			SEND_CONTACTS),

	HOW_TO_ADOPT_DOG
			(MAIN_MENU,
			DATING_RULES,
			REQUIRED_DOCUMENTS,
			TRANSPORTING_RULLES,
			IF_YOUNG_PET,
			IF_ADULT_PET,
			IF_HANDICAPPED_PET,
			KENOLOGIST_RECOMMENDS,
			KENOLOGIST_ADVICE,
			REASONS_FOR_REFUSAL,
			SEND_CONTACTS),
	
	UNKNOWN(new InlineKeyboardButton("Да").callbackData(MessageConstants.CALL_VOLUNTEER)),
	NEWUSER(new InlineKeyboardButton(MessageConstants.CAT_SHELTER).callbackData(MessageConstants.CAT_SHELTER),
			new InlineKeyboardButton(MessageConstants.DOG_SHELTER).callbackData(MessageConstants.DOG_SHELTER)),
	BACK (new InlineKeyboardButton("Назад").callbackData(MAIN_MENU));

	private final Keyboard keyboard;
	
	/**
	 * Конструктор inline кнопок (кнопки в самом чате)
	 * @param buttons кнопки из которых формируется меню
	 */
	ResponseMenu(InlineKeyboardButton... buttons) {
		this.keyboard = new InlineKeyboardMarkup(buttons);
	}

	/**
	 * Конструктор без параметров удаляет меню
	 */
	ResponseMenu() {
		this.keyboard = new ReplyKeyboardRemove();
	}

	/**
	 * Конструктор reply кнопок (раскрывающееся меню с кнопками)
	 * @param buttons кнопки из которых формируется меню
	 */
	ResponseMenu(String... buttons) {
		ReplyKeyboardMarkup keyboard = null;
		for(String button : buttons) {
			if(keyboard == null) {
				keyboard = new ReplyKeyboardMarkup(button);
			} else {
				if(button.equals(MessageConstants.CALL_VOLUNTEER) || button.equals(MessageConstants.SEND_CONTACTS)) {
					keyboard.addRow(new KeyboardButton(button).requestContact(true));
				} else keyboard.addRow(button);
			}
		}

		if(keyboard != null) {
			keyboard.selective(true);
			keyboard.resizeKeyboard(true);
			keyboard.oneTimeKeyboard(false);
		}
		
		this.keyboard = keyboard;
	}

	/**
	 * Получение кнопок для подключения их к ответу для пользователя
	 * @return экземпляр сформированного меню
	 */
	public Keyboard getKeyboard() {
		return this.keyboard;
	}
}
