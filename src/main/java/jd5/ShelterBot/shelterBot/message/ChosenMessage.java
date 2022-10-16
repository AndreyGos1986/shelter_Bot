package jd5.ShelterBot.shelterBot.message;


import com.pengrad.telegrambot.model.User;
import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.model.ShelterType;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.service.UserService;

public class ChosenMessage extends AbstractMessage {

    private UserService userService;

    private final ShelterType type;
    public ChosenMessage(ShelterType type) {
        this.type = type;
    }

    @Override
    public String getMessageText() {
        if(type == ShelterType.CATS) {
            return getMessageService().getResponseMessage(MessageConstants.CAT_SHELTER);
        } else return getMessageService().getResponseMessage(MessageConstants.DOG_SHELTER);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean send(UserMessage userMessage) {
        registerNewUser(userMessage.getUser());
        return super.send(userMessage);
    }

    private ShelterUser registerNewUser(User telegramUser) {
        ShelterUser user = new ShelterUser();
        user.setName(telegramUser.firstName());
        user.setSurname(telegramUser.lastName());
        user.setTelegramId(telegramUser.id());
        user.setShelterType(type);
        return userService.saveUser(user);
    }

}
