package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.controller.ShelterController;
import jd5.ShelterBot.shelterBot.repository.ParentRepository;
import jd5.ShelterBot.shelterBot.repository.ReportRepository;
import jd5.ShelterBot.shelterBot.repository.UserRepository;
import jd5.ShelterBot.shelterBot.service.ReportService;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterController.class)
public class ShelterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    ParentRepository parentRepository;
    @MockBean
    ReportRepository reportRepository;

    @MockBean
    ShelterController shelterController;


    public ShelterControllerTest() {
    }

        @AfterEach
    public void resetDb() {
        userRepository.deleteAll();
    }
    private String message = "Welcome";

    @Test
    public void ShelterControllerTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/test");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(message, "Welcome");

    }
}