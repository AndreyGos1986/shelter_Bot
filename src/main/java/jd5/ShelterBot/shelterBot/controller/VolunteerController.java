package jd5.ShelterBot.shelterBot.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jd5.ShelterBot.shelterBot.UserNotFoundException;
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

    public VolunteerController(ReportService reportService, UserService userService, VolunteerCallingService volunteerCallingService) {
        this.reportService = reportService;
        this.userService = userService;
        this.volunteerCallingService = volunteerCallingService;
    }
    @Operation(summary = "Поиск усыновителя по идентификатору")
    @GetMapping("findParentById")
    public ResponseEntity<ParentUser> findParentById(long parentId) {
        ParentUser user = userService.findParentById(parentId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получить всех пользователей по одному типу",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ShelterUser.class))
                    )
            ),
    })
    @GetMapping("findAllByType")
    public ResponseEntity<List<ShelterUser>> findAllByType(ShelterType type) {
        return ResponseEntity.ok(userService.findAllByType(type));
    }
    @Operation(summary = "Регистрация нового усыновителя")
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Вывести всех усыновителей",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ParentUser.class))
                    )
            ),
    })
    @GetMapping("findAllParents")
    public ResponseEntity<List<ParentUser>> findAllUsers() {
        List<ParentUser> list = userService.findAllParents();
        return ResponseEntity.ok(list);
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получить всех пользователей по одному типу",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = VolunteerCalling.class))
                    )
            ),
    })
    @GetMapping("findAllNewCalls")
    public ResponseEntity<List<VolunteerCalling>> findAllNewCalls(ShelterType type) {
        List<VolunteerCalling> list = volunteerCallingService.findNewCalls(type);
        return ResponseEntity.ok(list);
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получить список отчетов по определённому статусу ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Report.class))
                    )
            ),
    })
    @GetMapping("findReports")
    public ResponseEntity<List<Report>> findReports(ReportStatus status) {
        List<Report> list = reportService.findReportsWithStatus(status);
        return ResponseEntity.ok(list);
    }
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получить список отчетов по определённому усыновителю ",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Report.class))
                    )
            ),
    })
    @GetMapping("findAllReportsByParentId")
    public ResponseEntity<List<Report>> findAllReportsByParentId(ReportStatus status, long parentId) {
        return ResponseEntity.ok(reportService.findAllReportsByParentId(status, parentId));
    }
    /**
     * Метод присвоения статуса отчету, принимающий в парметры
     * @param reportId идентификатор отчета, которому необходимо изменить статус и
     * @param status статус, коротый будет присвоен найденному отчёту
     * @return результат о присвоении статуса
     */
    @GetMapping("setReportStatus")
    public ResponseEntity<Report> setReportStatus(long reportId, ReportStatus status) {
        return ResponseEntity.ok(reportService.setReportStatus(reportId, status));
    }
    /**
     * Метод получения фото из отчёта, принимающий в качестве параметра
     * @param reportId идентификатор отчёта. Если отчёт не найден, возвращается результат ResponseEntity.notFound().build(), если найден
     * @return возвращается ResponseEntity.ok().headers(headers).body(data)
     */
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
    /**
     * Метод для отправки сообщения пользователю,принимающий в параметрах:
     * @param userId идентификатор пользователя, которому надо отправить сообщение и
     * @param message само сообщение, если пользователь найден
     * @return возвращается результат, что сообщение отправлено
     */
    @GetMapping("sendMessageToUser")
    public ResponseEntity<String> sendMessageToUser(Long userId, String message) {
        try {
            reportService.sendMessageToUser(userId, message);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("The message is sent");
    }

}
