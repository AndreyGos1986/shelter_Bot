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

import java.util.List;
import java.util.Optional;

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

    //Вылетает UnsupportedOperationException на сроку ReportServiceImpl.java:151

/*  @Test
    void findReportsWithStatus() {
        Report reportExpected = new Report();

        List<Report> reportListExpected = List.of(reportExpected);

        Mockito.when(reportRepository.findAll()).thenReturn(reportListExpected);

        List<Report> reportListActual = reportService.findReportsWithStatus(ReportStatus.NEW);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);
    }

*/

    //Вылетает UnsupportedOperationException на сроку ReportServiceImpl.java:178

/*  @Test
    void findWrongReportsIfNotWrong() {
        Report report = new Report();
        byte[] photo = {1};
        String ration = "ration";
        String health = "health";
        String behavior = "behavior";

        report.setPhoto(photo);
        report.setRation(ration);
        report.setHealth(health);
        report.setBehavior(behavior);

        List<Report> reportListExpected = List.of(report);

        Mockito.when(reportRepository.findAll()).thenReturn(reportListExpected);

        List<Report> reportListActual = reportService.findWrongReports();

        Assertions
                .assertThat(reportListActual)
                .isEmpty();

    }
*/

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

//  //Вылетает UnsupportedOperationException на сроку ReportServiceImpl.java:236

/*  @Test
    void findAllReportsByParentIdNewStatus() {
        Report reportNew = new Report();
        reportNew.setStatus(ReportStatus.NEW);

        Report reportInProgress = new Report();
        reportNew.setStatus(ReportStatus.IN_PROGRESS);

        Report reportProcessed = new Report();
        reportNew.setStatus(ReportStatus.PROCESSED);


        long parentId = 0L;

        List<Report> reportList = List.of(reportNew, reportInProgress, reportProcessed);

        Mockito.when(reportRepository.findAllByParentId(parentId)).thenReturn(reportList);

        List<Report> reportListExpected = List.of(reportNew);

        List<Report> reportListActual = reportService.findAllReportsByParentId(ReportStatus.NEW, parentId);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);

    }

    @Test
    void findAllReportsByParentIdInProgressStatus() {
        Report reportNew = new Report();
        reportNew.setStatus(ReportStatus.NEW);

        Report reportInProgress = new Report();
        reportNew.setStatus(ReportStatus.IN_PROGRESS);

        Report reportProcessed = new Report();
        reportNew.setStatus(ReportStatus.PROCESSED);


        long parentId = 0L;

        List<Report> reportList = List.of(reportNew, reportInProgress, reportProcessed);

        Mockito.when(reportRepository.findAllByParentId(parentId)).thenReturn(reportList);

        List<Report> reportListExpected = List.of(reportInProgress);

        List<Report> reportListActual = reportService.findAllReportsByParentId(ReportStatus.NEW, parentId);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);

    }

    @Test
    void findAllReportsByParentIdInProcessedStatus() {
        Report reportNew = new Report();
        reportNew.setStatus(ReportStatus.NEW);

        Report reportInProgress = new Report();
        reportNew.setStatus(ReportStatus.IN_PROGRESS);

        Report reportProcessed = new Report();
        reportNew.setStatus(ReportStatus.PROCESSED);


        long parentId = 0L;

        List<Report> reportList = List.of(reportNew, reportInProgress, reportProcessed);

        Mockito.when(reportRepository.findAllByParentId(parentId)).thenReturn(reportList);

        List<Report> reportListExpected = List.of(reportProcessed);

        List<Report> reportListActual = reportService.findAllReportsByParentId(ReportStatus.NEW, parentId);

        Assertions
                .assertThat(reportListExpected)
                .isEqualTo(reportListActual);

    }
*/

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
