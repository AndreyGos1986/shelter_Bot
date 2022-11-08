package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.controller.VolunteerController;
import jd5.ShelterBot.shelterBot.model.ReportStatus;
import jd5.ShelterBot.shelterBot.repository.ParentRepository;
import jd5.ShelterBot.shelterBot.repository.ReportRepository;
import jd5.ShelterBot.shelterBot.repository.UserRepository;
import jd5.ShelterBot.shelterBot.service.ReportService;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static jd5.ShelterBot.shelterBot.ConstantsForTest.*;
import static jd5.ShelterBot.shelterBot.model.ShelterType.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = VolunteerController.class)
public class VolunteerControllerTest {


    @MockBean
    UserRepository userRepository;
    @MockBean
    ParentRepository parentRepository;
    @MockBean
    ReportRepository reportRepository;
    @MockBean
    private ReportService reportService;
    @MockBean
    private VolunteerCallingService volunteerCallingService;

    @MockBean
    private UserService userService;

    public VolunteerControllerTest() {
    }

    @Test
    public void VolunteerControllerTest() {
        final long dogsParentsId = 66L;


        when(userService.findParentById(dogsParentsId)).thenReturn(PARENT_USER_1);
        when(userService.findAllByType(CAT_SHELTER)).thenReturn(CATS_SHELTERS_LIST);
        when(userService.findAllParents()).thenReturn(PARENT_USER_LIST);
        when(volunteerCallingService.findNewCalls(DOG_SHELTER)).thenReturn(VOLUNTEER_CALLING_BY_DOGS_SHELTER);
        when(reportService.findReportsWithStatus(ReportStatus.NEW)).thenReturn(REPORT_LIST);
        when(reportService.findAllReportsByParentId(ReportStatus.NEW, 66L)).thenReturn(REPORT_LIST_BY_DOG_PARENT);
        when(reportService.getReportPhoto(DOG_REPORT.getId())).thenReturn(DOG_PHOTO);
    }
}

