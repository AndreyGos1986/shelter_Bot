package jd5.ShelterBot.shelterBot.constants;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum BotMessageEnum {

    //ответы на команды с клавиатуры
    HELP_MESSAGE("\uD83D\uDC4B Привет, я бот, помогающий при приёме из приюта питомца\n\n" +
            "❗ *Что Вы можете сделать:*\n" +
            "✅ Узнать о нашем приюте\n" +
            "✅ Выбрать питомца\n" +
            "✅ Отправлять с моей помощью ежедневные отчёты\n\n" +
            "Удачи!\n\n" +
            "Воспользуйтесь клавиатурой, чтобы начать работу\uD83D\uDC47"),
    TELL_ABOUT_SHELTER("\uD83D\uDC4D Информация о приюте"),
    SHELTER_SCHEDULE_ADDRESS("Мой адрес не дом и не улица\uD83D\uDC47 "),
    RECOMMENDATIONS_ON_SAFETY("Безопасность превыше всего!"),
    GET_AND_SAVE_CONTACT_INFO("Тут нужно заявку обработать как-то\uD83D\uDC47"),
    BACK("Тут нужно обработать \"Назад\"");

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}