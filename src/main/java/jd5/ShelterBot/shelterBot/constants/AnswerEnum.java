package jd5.ShelterBot.shelterBot.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Список шаблонных ответов
 */
@AllArgsConstructor
@Getter
public enum AnswerEnum {
    GREETING_INFO("Я бот этого приюта, внизу есть кнопочки, потыкай, если кнопочек нет, то справа около смайлика есть четыре точки в квадрате, нажми их"),
    GET_INFO_ANSWER("Нуууууу это приют, тут кошечки, собачки, можно забрать к себе домой, вооооооооот"),
    HOW_TO_ADOPT_ANSWER("Пришел, увидел, взял на ручки подержать, подписал бумажки, забрал домой, фоточки потом месяц сюда покидаешь и свободен"),
    SUBMIT_A_PET_REPORT_ANSWER("Кидай фотку, я поймаю"),
    CALL_A_VOLUNTEER_ANSWER("Позвал, сейчас кабанчиком подскочет"),
    DEFAULT_ANSWER("А все, а больше я ничего не умею");

    private final String answerText;

}
