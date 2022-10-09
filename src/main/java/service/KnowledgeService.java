package service;

import model.Knowledge;

import java.util.Collection;

public interface KnowledgeService {
    Knowledge addKnowledge(Knowledge newKnowledge); // метод создания нового з

    Knowledge findKnowledgeById(Long id); //получить информацию по идентификатору

    String findAnswerByQuestion(String question); // получить/найти ответ на вопрос

    String findAnswerByCodeId(String codeId); // найти вопрос по идентификаицонному коду

    Knowledge updateKnowledgeById(Long id); //обновить информацию

    void deleteKnowledgeById(Long id); //удалить информацию

    Collection<Knowledge> findAllKnowledge(); //получить всю информацию/всезнания

    Collection<String> findAllAnswersToAllQuestions(); //вывести ответы на все вопросы
}
