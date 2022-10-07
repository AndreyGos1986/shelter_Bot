package service;

import model.Knowledge;

import java.util.Collection;

public interface KnowledgeService {
    Knowledge addKnowledge(Knowledge newKnowledge);

    Knowledge findKnowledgeById(Long id);

    String findAnswerByQuestion(String question);

    String findAnswerByCodeId(String codeId);

    Knowledge updateKnowledgeById(Long id);

    void deleteKnowledgeById(Long id);

    Collection<Knowledge> findAllKnowledge();

    Collection<String> findAllAnswersToAllQuestions();
}