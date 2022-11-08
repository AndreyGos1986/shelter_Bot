package jd5.ShelterBot.shelterBot.service;


import jd5.ShelterBot.shelterBot.model.Knowledge;

import java.util.Collection;

public interface KnowledgeService {
    Knowledge addKnowledge(Knowledge newKnowledge); // метод внесения новой информации

    Knowledge findKnowledgeById(Long id);//получить информацию по идентификатору

    String findAnswerByQuestion(String question); // получить/найти ответ на вопрос

    String findAnswerByCodeId(String codeId); // найти вопрос по идентификаицонному коду

    Knowledge updateKnowledgeById(Long id); //обновить информацию

    void deleteKnowledgeById(Long id); //удалить информацию

    Collection<Knowledge> findAllKnowledge(); //получить всю информацию/все знания

    Collection<String> findAllAnswersToAllQuestions(); //вывести ответы на все вопросы

}
