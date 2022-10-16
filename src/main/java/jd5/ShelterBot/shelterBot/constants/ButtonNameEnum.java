package jd5.ShelterBot.shelterBot.constants;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    TELL_ABOUT_SHELTER("Информация о приюте"),
    SHELTER_SCHEDULE_ADDRESS("График работы и адрес"),
    RECOMMENDATIONS_ON_SAFETY("ТБ в приюте"),
    GET_AND_SAVE_CONTACT_INFO("Оставить заявку для связи"),
    BACK("Назад"),

    HELP_BUTTON("Помощь");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}