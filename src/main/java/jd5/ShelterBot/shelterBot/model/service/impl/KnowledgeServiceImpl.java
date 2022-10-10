package jd5.ShelterBot.shelterBot.model.service.impl;

import jd5.ShelterBot.shelterBot.model.Knowledge;
import jd5.ShelterBot.shelterBot.model.service.KnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jd5.ShelterBot.shelterBot.repository.KnowledgeRepository;

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
        logger.info("Запущен метод добавления/создания новой информации");
        return knowledgeRepository.save(newKnowledge);
    }

    /**
     * Метод поиска информации по идентификатору
     * @param id
     * @return
     */
    @Override
    public Knowledge findKnowledgeById(Long id) {
        return knowledgeRepository.findById(id).get();
    }

    /**
     * получить ответ на вопрос
     * @param questionToFind
     * @return
     */
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

    /**
     * Обновить информацию
     * @param id
     * @return
     */
    @Override
    public Knowledge updateKnowledgeById(Long id) {
        return knowledgeRepository.findById(id).get();
    }

    /**
     * Удалить информацию
     * @param id
     */
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
        return "Вопрос:" + question +
                "Ответ:" + answer + '\'';

    }
}