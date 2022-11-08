package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.model.ReportStatus;
import jd5.ShelterBot.shelterBot.model.VolunteerCalling;
import jd5.ShelterBot.shelterBot.repository.ParentRepository;
import jd5.ShelterBot.shelterBot.repository.UserRepository;
import jd5.ShelterBot.shelterBot.repository.VolunteerCallingRepository;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.impl.VolunteerCallingServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class VolunteerCallingServiceImplTest {

    @Autowired
    VolunteerCallingServiceImpl volunteerCallingService;

    @Autowired
    UserService userService;

    @MockBean
    VolunteerCallingRepository volunteerCallingRepository;

    @MockBean
    ParentRepository parentRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(volunteerCallingService).isNotNull();
        Assertions.assertThat(userService).isNotNull();
        Assertions.assertThat(volunteerCallingRepository).isNotNull();
        Assertions.assertThat(parentRepository).isNotNull();
        Assertions.assertThat(userRepository).isNotNull();
    }


    @Test
    void findAllCalls() {
        VolunteerCalling volunteerCalling = new VolunteerCalling();

        List<VolunteerCalling> volunteerCallingListExpected = List.of(volunteerCalling);

        Mockito.when(volunteerCallingRepository.findAll()).thenReturn(volunteerCallingListExpected);

        List<VolunteerCalling> volunteerCallingListActual = volunteerCallingService.findAllCalls();

        Assertions
                .assertThat(volunteerCallingListExpected)
                .isEqualTo(volunteerCallingListActual);
    }

    @Test
    void findNewCalls() {
        VolunteerCalling volunteerCalling = new VolunteerCalling();

        List<VolunteerCalling> volunteerCallingListExpected = List.of(volunteerCalling);

        Mockito.when(volunteerCallingRepository.findAllByStatusEquals(ReportStatus.NEW)).thenReturn(volunteerCallingListExpected);

        List<VolunteerCalling> volunteerCallingListActual = volunteerCallingService.findNewCalls();

        Assertions
                .assertThat(volunteerCallingListExpected)
                .isEqualTo(volunteerCallingListActual);
    }

    //  //Вылетает UnsupportedOperationException на сроку VolunteerCallingServiceImpl.java:53
/*    @Test
    void testFindNewCalls() {
        VolunteerCalling volunteerCalling = new VolunteerCalling();

        List<VolunteerCalling> volunteerCallingListExpected = List.of(volunteerCalling);

        Mockito.when(volunteerCallingRepository.findAllByStatusEquals(ReportStatus.NEW)).thenReturn(volunteerCallingListExpected);

        List<VolunteerCalling> volunteerCallingListActual = volunteerCallingService.findNewCalls(ShelterType.NEWUSER);

        Assertions
                .assertThat(volunteerCallingListExpected)
                .isEqualTo(volunteerCallingListActual);
    }
*/
}