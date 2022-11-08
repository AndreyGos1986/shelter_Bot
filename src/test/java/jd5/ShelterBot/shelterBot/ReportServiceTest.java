package jd5.ShelterBot.shelterBot;

import jd5.ShelterBot.shelterBot.model.ParentUser;
import jd5.ShelterBot.shelterBot.model.Report;
import jd5.ShelterBot.shelterBot.model.ReportStatus;
import jd5.ShelterBot.shelterBot.model.ShelterUser;
import jd5.ShelterBot.shelterBot.repository.ParentRepository;
import jd5.ShelterBot.shelterBot.repository.ReportRepository;
import jd5.ShelterBot.shelterBot.repository.UserRepository;
import jd5.ShelterBot.shelterBot.service.impl.ReportServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class ReportServiceTest {

    @Autowired
    ReportServiceImpl reportService;

    @MockBean
    ReportRepository reportRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ParentRepository parentRepository;

    @Test
    void contextLoads() {
        Assertions.assertThat(reportService).isNotNull();
        Assertions.assertThat(reportRepository).isNotNull();
        Assertions.assertThat(userRepository).isNotNull();
        Assertions.assertThat(parentRepository).isNotNull();
    }

    @Test
    void findReportById() {
        Report reportExpected = new Report();
        long reportId = 0L;

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(reportExpected));

        Report reportActual = reportService.findReportById(reportId);

        Assertions
                .assertThat(reportExpected)
                .isEqualTo(reportActual);
    }

    @Test
    void findAllReports() {
        Report reportExpected = new Report();
        List<Report> reportListExpected = List.of(reportExpected);

        Mockito.when(reportRepository.findAll()).thenReturn(reportListExpected);

        List<Report> reportListActual = reportService.findAllReports();

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);
    }


    @Test
    void findReportsWithStatus() {
        Report reportExpected = new Report();
        Report reportExpectedTwo = new Report();

        List<Report> reportListExpected = List.of(reportExpected, reportExpectedTwo);

        Mockito.when(reportRepository.findAll()).thenReturn(reportListExpected);

        List<Report> reportListActual = reportService.findReportsWithStatus(ReportStatus.NEW);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);
    }


    //Вылетает UnsupportedOperationException на сроку ReportServiceImpl.java:178

    @Test
    void findWrongReportsIfNotWrong() {
        Report report = new Report();
        Report report1 = new Report();

        byte[] photo = {1};
        String ration = "ration";
        String health = "health";
        String behavior = "behavior";

        byte[] photo1 = {1};
        String ration1 = "ration";
        String health1 = "health";
        String behavior1 = "behavior";

        report.setPhoto(photo);
        report.setRation(ration);
        report.setHealth(health);
        report.setBehavior(behavior);

        report1.setPhoto(photo1);
        report1.setRation(ration1);
        report1.setHealth(health1);
        report1.setBehavior(behavior1);


        List<Report> reportListExpected = List.of(report, report1);

        Mockito.when(reportRepository.findAll()).thenReturn(reportListExpected);

        List<Report> reportListActual = reportService.findWrongReports();

        Assertions
                .assertThat(reportListActual)
                .isEqualTo(reportListExpected);

        Assertions
                .assertThat(reportListActual.isEmpty() == false);

    }


    @Test
    void getReportPhotoWhenPhotoExist() {
        Report reportExpected = new Report();
        byte[] photo = {1};
        reportExpected.setPhoto(photo);

        long reportId = 0L;

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(reportExpected));

        byte[] resultActual = reportService.getReportPhoto(reportId);

        Assertions
                .assertThat(photo)
                .isEqualTo(resultActual);

    }

    @Test
    void getReportPhotoWhenPhotoNotExist() {
        Report reportExpected = new Report();

        long reportId = 0L;

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(reportExpected));

        byte[] resultExpected = new byte[0];
        byte[] resultActual = reportService.getReportPhoto(reportId);

        Assertions
                .assertThat(resultExpected)
                .isEqualTo(resultActual);

    }

    @Test
    void isReportingAllowedTrue() {
        ShelterUser shelterUser = new ShelterUser();
        long shelterUserId = 0L;
        shelterUser.setId(shelterUserId);

        ParentUser parentUser = new ParentUser();
        long telegramId = 1L;

        Mockito.when(userRepository.findUserByTelegramId(telegramId)).thenReturn(shelterUser);
        Mockito.when(parentRepository.findByShelterUserId(shelterUserId)).thenReturn(parentUser);

        Assertions
                .assertThat(reportService.isReportingAllowed(telegramId))
                .isTrue();
    }

    @Test
    void isReportingAllowedFalse() {

        long telegramId = 1L;

        Mockito.when(userRepository.findUserByTelegramId(telegramId)).thenReturn(null);

        Assertions
                .assertThat(reportService.isReportingAllowed(telegramId))
                .isFalse();
    }


            //ГОТОВ
    @Test
    void findAllReportsByParentIdNewStatus() {
        long parentId = 1L;

        byte[] photo = {1};
        String ration = "ration";
        String health = "health";
        String behavior = "behavior";
        ReportStatus newStatus = ReportStatus.NEW;

        byte[] photo1 = {2};
        String ration1 = "ration";
        String health1 = "health";
        String behavior1 = "behavior";
        ReportStatus newStatus1 = ReportStatus.NEW;

        Report reportNew1 = new Report(0L, parentId, photo, ration, health, behavior, newStatus, LocalDate.now());
        Report reportNew = new Report(1L, parentId, photo1, ration1, health1, behavior1, newStatus1, LocalDate.now());

        List<Report> reportListByParent = List.of(reportNew1, reportNew);

        Mockito.when(reportRepository.findAllByParentId(parentId)).thenReturn(reportListByParent);

        List<Report> reportListExpected = List.of(reportNew1, reportNew);

        List<Report> reportListActual = reportService.findAllReportsByParentId(ReportStatus.NEW, parentId);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);

    }
//ГОТОВ
    @Test
    void findAllReportsByParentIdInProgressStatus() {
        long parentId = 0L;

        byte[] photo = {1};
        String ration = "ration";
        String health = "health";
        String behavior = "behavior";
        ReportStatus progress = ReportStatus.IN_PROGRESS;

        byte[] photo1 = {2};
        String ration1 = "ration";
        String health1 = "health";
        String behavior1 = "behavior";
        ReportStatus progress1 = ReportStatus.IN_PROGRESS;

        Report reportInProgress1 = new Report(1L, parentId, photo, ration, health, behavior, progress, LocalDate.now());
        Report reportInProgress = new Report(0L, parentId, photo1, ration1, health1, behavior1, progress1, LocalDate.now());


        List<Report> reportListByParent = List.of(reportInProgress,reportInProgress1);
        Mockito.when(reportRepository.findAllByParentId(parentId)).thenReturn(reportListByParent);

        List<Report> reportListExpected = List.of(reportInProgress,reportInProgress1);

        List<Report> reportListActual = reportService.findAllReportsByParentId(ReportStatus.IN_PROGRESS, parentId);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);

    }

    @Test
    void findAllReportsByParentIdInProcessedStatus() {
        long parentId = 1L;

        byte[] photo = {1};
        String ration = "ration";
        String health = "health";
        String behavior = "behavior";
        ReportStatus processedStatus = ReportStatus.PROCESSED;

        byte[] photo1 = {2};
        String ration1 = "ration";
        String health1 = "health";
        String behavior1 = "behavior";
        ReportStatus processedStatus1 = ReportStatus.PROCESSED;

        ReportStatus filterStatus = ReportStatus.PROCESSED;

        Report reportProcessed= new Report(0L, parentId, photo, ration, health, behavior, processedStatus, LocalDate.now());
        Report reportProcessed2 = new Report(1L, parentId, photo1, ration1, health1, behavior1, processedStatus1, LocalDate.now());



        List<Report> reportListByParent = List.of(reportProcessed,reportProcessed2);

        Mockito.when(reportRepository.findAllByParentId(parentId)).thenReturn(reportListByParent);

        List<Report> reportListExpected = List.of(reportProcessed,reportProcessed2);
//                reportListByParent.stream().
//                filter(report -> report.getStatus()==filterStatus).collect(Collectors.toList());

        List<Report> reportListActual = reportService.findAllReportsByParentId(filterStatus, parentId);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);

    }


    @Test
    void setReportStatus() {
        Report report = new Report();
        long reportId = 0L;

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        Report reportExpected = new Report();
        reportExpected.setStatus(ReportStatus.NEW);

        Report reportActual = reportService.setReportStatus(reportId, ReportStatus.NEW);

        Assertions
                .assertThat(reportExpected.getStatus())
                .isEqualTo(reportActual.getStatus());
    }
}
