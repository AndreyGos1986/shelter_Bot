package jd5.ShelterBot.shelterBot.handler;


import jd5.ShelterBot.shelterBot.message.MessageConstants;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;

import java.util.HashMap;

public class CatMap extends HashMap<String, ResponseMessage> {

	public void init() {
		// инициализация карты сообщений
        put(MessageConstants.MAIN_MENU, ResponseMessage.MAIN_MENU_MESSAGE);

        // Basic
        put(MessageConstants.USER_CONTACTS, ResponseMessage.TAKE_CONTACT);
        
        // Главноме меню
        put(MessageConstants.SHELTER_INFO, ResponseMessage.CATS_SHELTER_INFO_MESSAGE);
        put(MessageConstants.HOW_TO_ADOPT, ResponseMessage.CATS_ADOPT_MESSAGE);
        put(MessageConstants.SEND_REPORT, ResponseMessage.SEND_REPORT_MESSAGE);
        
        // Информация о приюте
        put(MessageConstants.SHELTER_DESCRIPTION, ResponseMessage.CAT_SHELTER_DESCRIPTION);
        put(MessageConstants.SHELTER_ADDRESS, ResponseMessage.CAT_SHELTER_ADDRESS);
        put(MessageConstants.SHELTER_SUCURITY_CONTACTS, ResponseMessage.CAT_SHELTER_CONTACTS);
        put(MessageConstants.SHELTER_RECOMMENDS, ResponseMessage.CAT_SHELTER_RECOMMENDS);

        // Как взять кошку

            put(MessageConstants.DATING_RULES, ResponseMessage.CATS_DATING_RULES);
            put(MessageConstants.REQUIRED_DOCUMENTS, ResponseMessage.CATS_REQUIRED_DOCUMENTS);
            put(MessageConstants.TRANSPORTING_RULLES, ResponseMessage.CATS_TRANSPORTING_RULES);
            put(MessageConstants.IF_YOUNG_PET, ResponseMessage.CATS_IF_YOUNG);
            put(MessageConstants.IF_ADULT_PET, ResponseMessage.CATS_IF_ADULT);
            put(MessageConstants.IF_HANDICAPPED_PET, ResponseMessage.CATS_IF_HANDICAPPED);
            put(MessageConstants.REASONS_FOR_REFUSAL, ResponseMessage.CATS_REASONS_FOR_REFUSAL);
    }
}
