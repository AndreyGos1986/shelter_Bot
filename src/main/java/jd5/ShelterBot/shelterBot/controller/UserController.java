package jd5.ShelterBot.shelterBot.controller;

import io.swagger.v3.oas.annotations.Operation;
import jd5.ShelterBot.shelterBot.model.BotResponse;
import jd5.ShelterBot.shelterBot.service.BotResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("BotResponse")
public class UserController {

    private final BotResponseService botResponseService;

    public UserController(BotResponseService botResponseService) {
        this.botResponseService = botResponseService;
    }

    @Operation(summary = "Приветствие")
    @GetMapping("test")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Привет!!!!");
    }

    @Operation(summary = "Получить ответ на запрос",
            tags = "")
    @GetMapping("test/bot_response")
    public ResponseEntity<String> getResponseMessage(String message) {
        String response = botResponseService.getResponseMessage(message);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Сохранить новое сообщение-ответ от бота",
            tags = "Сохранить")
    @PostMapping("test/bot_response")
    public ResponseEntity<BotResponse> getResponseMessage(String keyMessage, String responseMessage) {
        BotResponse response = botResponseService.saveResponseMessage(keyMessage, responseMessage);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}