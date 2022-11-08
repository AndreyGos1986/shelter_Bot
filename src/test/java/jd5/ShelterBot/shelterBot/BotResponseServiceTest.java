package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.model.BotResponse;
import jd5.ShelterBot.shelterBot.repository.BotResponseRepository;
import jd5.ShelterBot.shelterBot.service.impl.BotResponseServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class BotResponseServiceTest {

    @Autowired
    BotResponseServiceImpl botResponseService;

    @MockBean
    BotResponseRepository shelterRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(botResponseService).isNotNull();
        Assertions.assertThat(shelterRepository).isNotNull();
    }

    @Test
    public void getResponseMessageTest() {

        String keyMessage = "question";
        String responseMessage = "answer";
        BotResponse botResponseTest = new BotResponse(keyMessage, responseMessage);


        Mockito.when(shelterRepository.findBotResponseByKeyMessage(keyMessage)).thenReturn(botResponseTest);

        String responseExpected  = botResponseTest.getResponseMessage();

        String responseActual = botResponseService.getResponseMessage(keyMessage);

        Assertions
                .assertThat(responseExpected)
                .isEqualTo(responseActual);

    }

    @Test
    public void getResponseMessageTestWhenNull() {
        Assertions
                .assertThat(botResponseService.getResponseMessage(null))
                .isNull();
    }

    @Test
    public void saveResponseMessageTest() {

        String keyMessage = "question";
        String responseMessage = "answer";
        BotResponse botResponseExpected = new BotResponse(keyMessage, responseMessage);

        Mockito.when(shelterRepository.save(botResponseExpected)).thenReturn(botResponseExpected);

        BotResponse botResponseActual = botResponseService.saveResponseMessage(keyMessage, responseMessage);

        Assertions
                .assertThat(botResponseExpected)
                .isEqualTo(botResponseActual);
    }

}
