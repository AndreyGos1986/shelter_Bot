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
public class ShelterController {

    private final BotResponseService shelterService;

    public ShelterController(BotResponseService shelterService) {
        this.shelterService = shelterService;
    }
@Operation (summary = "Приветствие")
    @GetMapping("test")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome");
    }

    @Operation (summary = "Получить ответное сообщение")
    @GetMapping("test/bot_response")
    public ResponseEntity<String> getResponseMessage(String message) {
        String response = shelterService.getResponseMessage(message);
        if(response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("test/bot_response")
    public ResponseEntity<BotResponse> getResponseMessage(String keyMessage, String responseMessage) {
        BotResponse response = shelterService.saveResponseMessage(keyMessage, responseMessage);
        if(response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}

