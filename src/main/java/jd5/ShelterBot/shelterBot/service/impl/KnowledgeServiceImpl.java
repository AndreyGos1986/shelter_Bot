package jd5.ShelterBot.shelterBot.service.impl;

import jd5.ShelterBot.shelterBot.model.Knowledge;
import jd5.ShelterBot.shelterBot.service.KnowledgeService;
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

    /**
     * Метод добавления новой информации, принимающий в параметры
     * @param newKnowledge новую информацию, после чего
     * @return возвращается результат - сохранение новой информации в указанном репозитории
     */
    @Override
    public Knowledge addKnowledge(Knowledge newKnowledge) {
        logger.info("Запущен метод добавления/создания новой информации");
        return knowledgeRepository.save(newKnowledge);
    }

    /**
     * Метод поиска информации по идентификатору, принимающий в параметры
     * @param id идентификатор и
     * @return возвращающий найденную по идентификатору информацию
     */
    @Override
    public Knowledge findKnowledgeById(Long id) {
        return knowledgeRepository.findById(id).get();
    }

    /**
     * Метод выводящий ответ на вопрос, принимающий в параметры
     * @param questionToFind вопрос, после чего через метод репозитория осуществляется поиск и
     * @return и возвращающиий ответ
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


    @Override
    public Knowledge updateKnowledgeById(Long id) {
        return knowledgeRepository.findById(id).get();
    }

    /**
     * Метод удаления информации по идентификатору, принимающий в параемтры
     * @param id идентификатор удаляющий найденную информацию
     */
    @Override
    public void deleteKnowledgeById(Long id) {
        knowledgeRepository.deleteById(id);
    }

    /**
     * Метод, выводящий всю имеющуюся информацию
     * @return возвращающий список, содержащий всю информацию
     */
    @Override
    public Collection<Knowledge> findAllKnowledge() {
        return knowledgeRepository.findAll();
    }

    /**
     * Метод поиска всех ответов на все вопросы, в котором
     * инициализируется список всех вопросов
     * инициализируется список всех ответов
     * после чего начинается цикл и все вопросы добавляются в список вопросов
     * ответы - в список ответов
     * @return
     */
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