package jd5.ShelterBot.shelterBot.controller;


import jd5.ShelterBot.shelterBot.exceptions.UserNotFoundException;
import jd5.ShelterBot.shelterBot.handler.UserMessage;
import jd5.ShelterBot.shelterBot.model.*;
import jd5.ShelterBot.shelterBot.response.ResponseMessage;
import jd5.ShelterBot.shelterBot.service.ReportService;
import jd5.ShelterBot.shelterBot.service.UserService;
import jd5.ShelterBot.shelterBot.service.VolunteerCallingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {

    private final ReportService reportService;
    private final UserService userService;
    private final VolunteerCallingService volunteerCallingService;

    public VolunteerController(ReportService reportService,
                               UserService userService,
                               VolunteerCallingService volunteerCallingService) {
        this.reportService = reportService;
        this.userService = userService;
        this.volunteerCallingService = volunteerCallingService;
    }

    @GetMapping("findParentById")
    public ResponseEntity<ParentUser> findParentById(long parentId) {
    ParentUser user = userService.findParentById(parentId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("findAllByType")
    public ResponseEntity<List<ShelterUser>> findAllByType(ShelterType type) {
        return ResponseEntity.ok(userService.findAllByType(type));
    }

    @GetMapping("registerUserAsParent")
     public ResponseEntity<ParentUser> registerUserAsParent(long userId, String phoneNumber) {
        ParentUser user = userService.findParentByUserId(userId);
        if(user != null) {
            return ResponseEntity.ok(user);
        }

        user = userService.registerAsParent(userId, phoneNumber);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        reportService.sendMessageToUser(userId, "Поздравляем, Вы были зарегистрированы как усыновитель. Теперь Вам необходимо отправлять отчет каждый день в течении испытательного срока 1 месяца.");

        UserMessage message = new UserMessage();
        ShelterUser shelterUser = userService.findUserById(userId);
        message.setUserId(shelterUser.getTelegramId());
        ResponseMessage.MAIN_MENU_MESSAGE.send(message);
        return ResponseEntity.ok(user);
    }

    @GetMapping("findAllParents")
    public ResponseEntity<List<ParentUser>> findAllUsers() {
        List<ParentUser> list = userService.findAllParents();
        return ResponseEntity.ok(list);
    }

    @GetMapping("findAllNewCalls")
    public ResponseEntity<List<VolunteerCalling>> findAllNewCalls(ShelterType type) {
        List<VolunteerCalling> list = volunteerCallingService.findNewCalls(type);
        return ResponseEntity.ok(list);
    }

    @GetMapping("findReports")
    public ResponseEntity<List<Report>> findReports(ReportStatus status) {
        List<Report> list = reportService.findReportsWithStatus(status);
        return ResponseEntity.ok(list);
    }

    @GetMapping("findAllReportsByParentId")
    public ResponseEntity<List<Report>> findAllReportsByParentId(ReportStatus status, long parentId) {
        return ResponseEntity.ok(reportService.findAllReportsByParentId(status, parentId));
    }

    @GetMapping("setReportStatus")
    public ResponseEntity<Report> setReportStatus(long reportId, ReportStatus status) {
        return ResponseEntity.ok(reportService.setReportStatus(reportId, status));
    }

    @GetMapping("getReportPhoto")
    public ResponseEntity<byte[]> getReportPhoto(Long reportId) {
        byte[] data = reportService.getReportPhoto(reportId);
        if(data.length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(data.length);
        return ResponseEntity.ok().headers(headers).body(data);
    }

    @GetMapping("sendMessageToUser")
    public ResponseEntity<String> sendMessageToUser(Long userId, String message) {
        try {
            reportService.sendMessageToUser(userId, message);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Сообщение отправлено");
    }

}
