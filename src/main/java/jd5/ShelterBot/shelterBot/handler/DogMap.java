package jd5.ShelterBot.shelterBot.handler;


import jd5.ShelterBot.shelterBot.message.MessageConstants;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;

import java.util.HashMap;

public class DogMap extends HashMap<String, ResponseMessage> {

    public void init() {
        put(MessageConstants.MAIN_MENU, ResponseMessage.MAIN_MENU_MESSAGE);

        put(MessageConstants.USER_CONTACTS, ResponseMessage.TAKE_CONTACT);

        // Главное меню
        put(MessageConstants.SHELTER_INFO, ResponseMessage.DOGS_SHELTER_INFO_MESSAGE);
        put(MessageConstants.HOW_TO_ADOPT, ResponseMessage.DOGS_ADOPT_MESSAGE);
        put(MessageConstants.SEND_REPORT, ResponseMessage.SEND_REPORT_MESSAGE);

        // Информация о приюте
        put(MessageConstants.SHELTER_DESCRIPTION, ResponseMessage.DOGS_SHELTER_DESCRIPTION);
        put(MessageConstants.SHELTER_ADDRESS, ResponseMessage.DOGS_SHELTER_ADDRESS);
        put(MessageConstants.SHELTER_SUCURITY_CONTACTS, ResponseMessage.CAT_SHELTER_CONTACTS);
        put(MessageConstants.SHELTER_RECOMMENDS, ResponseMessage.DOGS_SHELTER_RECOMMENDS);

        // Как взять собаку

        put(MessageConstants.DATING_RULES, ResponseMessage.DOGS_DATING_RULES);
        put(MessageConstants.REQUIRED_DOCUMENTS, ResponseMessage.DOGS_REQUIRED_DOCUMENTS);
        put(MessageConstants.TRANSPORTING_RULLES, ResponseMessage.DOGS_TRANSPORTING_RULES);
        put(MessageConstants.IF_YOUNG_PET, ResponseMessage.DOGS_YOUNG);
        put(MessageConstants.IF_ADULT_PET, ResponseMessage.DOGS_ADULT);
        put(MessageConstants.IF_HANDICAPPED_PET, ResponseMessage.DOGS_HANDICAPPED);
        put(MessageConstants.KENOLOGIST_RECOMMENDS, ResponseMessage.DOGS_KENOLOGIST_RECOMMENDS);
        put(MessageConstants.KENOLOGIST_ADVICE, ResponseMessage.DOGS_KENOLOGIST_ADVICE);
        put(MessageConstants.REASONS_FOR_REFUSAL, ResponseMessage.DOGS_REASONS_FOR_REFUSAL);
    }

}
