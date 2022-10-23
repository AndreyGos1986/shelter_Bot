package jd5.ShelterBot.shelterBot.service.impl;


import jd5.ShelterBot.shelterBot.model.Knowledge;
import jd5.ShelterBot.shelterBot.repository.KnowledgeRepository;
import jd5.ShelterBot.shelterBot.service.KnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    Logger logger = LoggerFactory.getLogger(KnowledgeService.class);

    private final KnowledgeRepository knowledgeRepository;

    public KnowledgeServiceImpl(KnowledgeRepository knowledgeRepository) {
        this.knowledgeRepository = knowledgeRepository;
    }


    @Override

    public Knowledge addKnowledge(Knowledge newKnowledge) {
        logger.info("Was invoked method for creating new knowledge");
        return knowledgeRepository.save(newKnowledge);
    }

    @Override
    public Knowledge findKnowledgeById(Long id) {
        return knowledgeRepository.findById(id).get();
    }

    @Override
    public String findAnswerByQuestion(String questionToFind) {
        String foundAnswer = knowledgeRepository.findKnowledgeByQuestionContainingIgnoreCase(questionToFind).getAnswer();
        return foundAnswer;
    }

    @Override
    public String findAnswerByCodeId(String codeId) {
        String foundAnswer = knowledgeRepository.findKnowledgeByCodeId(codeId).getAnswer();
        return foundAnswer;
    }

    @Override
    public Knowledge updateKnowledgeById(Long id) {
        return knowledgeRepository.findById(id).get();
    }

    @Override
    public void deleteKnowledgeById(Long id) {
        knowledgeRepository.deleteById(id);
    }

    @Override
    public Collection<Knowledge> findAllKnowledge() {
        return knowledgeRepository.findAll();
    }

    @Override
    public Collection<String> findAllAnswersToAllQuestions() {
        List<String> allQuestionsById = new ArrayList<>();
        List<String> allAnswersById = new ArrayList<>();

        for (int i = 1; i <= findAllKnowledge().size(); i++) {
            Long idFind = i * 1L;
            allQuestionsById.add(findKnowledgeById(idFind).getQuestion());
            allAnswersById.add(findKnowledgeById(idFind).getAnswer());
        }

        return null;
    }

    private String printString(String question, String answer) {
        return  "Вопрос:" + question +
                "Ответ:" + answer + '\'';

    }

}
