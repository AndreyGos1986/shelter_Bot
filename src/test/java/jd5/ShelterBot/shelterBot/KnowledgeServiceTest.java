package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.model.Knowledge;
import jd5.ShelterBot.shelterBot.repository.KnowledgeRepository;
import jd5.ShelterBot.shelterBot.service.impl.KnowledgeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class KnowledgeServiceTest {

    @Autowired
    KnowledgeServiceImpl knowledgeService;

    @MockBean
    KnowledgeRepository knowledgeRepository;


    Long id = 0L;
    String codeId = "codeId";
    String question = "question";
    String answer = "answer";
    Long version = 1L;

    Knowledge knowledgeExpected = new Knowledge(id, codeId, question, answer, version, false, false);
    @Test
    void contextLoads() {
        Assertions.assertThat(knowledgeService).isNotNull();
        Assertions.assertThat(knowledgeRepository).isNotNull();
    }

    @Test
    void addKnowledge() {

        Mockito.when(knowledgeRepository.save(knowledgeExpected)).thenReturn(knowledgeExpected);

        Knowledge knowledgeActual = knowledgeService.addKnowledge(knowledgeExpected);

        Assertions
                .assertThat(knowledgeExpected)
                .isEqualTo(knowledgeActual);
    }

    @Test
    void findKnowledgeById() {

        Mockito.when(knowledgeRepository.findById(knowledgeExpected.getId())).thenReturn(Optional.ofNullable(knowledgeExpected));

        Knowledge knowledgeActual = knowledgeService.findKnowledgeById(knowledgeExpected.getId());

        Assertions
                .assertThat(knowledgeExpected)
                .isEqualTo(knowledgeActual);
    }

    @Test
    void findAnswerByQuestion() {

        Mockito.when(knowledgeRepository.findKnowledgeByQuestionContainingIgnoreCase(question)).thenReturn(knowledgeExpected);

        String knowledgeActual = knowledgeService.findAnswerByQuestion(question);

        Assertions
                .assertThat(knowledgeExpected.getAnswer())
                .isEqualTo(knowledgeActual);
    }

    @Test
    void findAnswerByCodeId() {

        Mockito.when(knowledgeRepository.findKnowledgeByCodeId(codeId)).thenReturn(knowledgeExpected);

        String knowledgeActual = knowledgeService.findAnswerByCodeId(codeId);

        Assertions
                .assertThat(knowledgeExpected.getAnswer())
                .isEqualTo(knowledgeActual);
    }

    @Test
    void updateKnowledgeById() {

        Mockito.when(knowledgeRepository.findById(knowledgeExpected.getId())).thenReturn(Optional.ofNullable(knowledgeExpected));

        Knowledge knowledgeActual = knowledgeService.updateKnowledgeById(knowledgeExpected.getId());

        Assertions
                .assertThat(knowledgeExpected)
                .isEqualTo(knowledgeActual);
    }

    @Test
    void findAllKnowledge() {
        List<Knowledge> knowledgeCollectionExpected = List.of(knowledgeExpected);

        Mockito.when(knowledgeRepository.findAll()).thenReturn(knowledgeCollectionExpected);

        Collection<Knowledge> knowledgeActual = knowledgeService.findAllKnowledge();

        Assertions
                .assertThat(knowledgeCollectionExpected)
                .isEqualTo(knowledgeActual);
    }
}