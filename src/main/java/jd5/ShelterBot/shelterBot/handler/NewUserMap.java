package jd5.ShelterBot.shelterBot.handler;

import jd5.ShelterBot.shelterBot.message.ChosenMessage;
import jd5.ShelterBot.shelterBot.message.MessageConstants;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;
import jd5.ShelterBot.shelterBot.service.UserService;

import java.util.HashMap;

public class NewUserMap extends HashMap<String, ResponseMessage> {

	public void init(UserService userService) {
        put(MessageConstants.BOT_START, ResponseMessage.WELCOME_MESSAGE);
		put(MessageConstants.CAT_SHELTER, ResponseMessage.CAT_SHELTER_CHOSEN);
		put(MessageConstants.DOG_SHELTER, ResponseMessage.DOG_SHELTER_CHOSEN);
		put(MessageConstants.USER_CONTACTS, ResponseMessage.TAKE_CONTACT);

		((ChosenMessage) ResponseMessage.CAT_SHELTER_CHOSEN.getMessage()).setUserService(userService);
		((ChosenMessage) ResponseMessage.DOG_SHELTER_CHOSEN.getMessage()).setUserService(userService);
	}

}
