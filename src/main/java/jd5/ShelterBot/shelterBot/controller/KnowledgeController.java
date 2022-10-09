package jd5.ShelterBot.shelterBot.controller;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jd5.ShelterBot.shelterBot.model.Knowledge;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jd5.ShelterBot.shelterBot.service.KnowledgeService;

import java.util.Collection;

@RestController
@RequestMapping("knowledge")
public class KnowledgeController {

    private final KnowledgeService service;

    public KnowledgeController(KnowledgeService service) {
        this.service = service;
    }
    @ApiResponses({
            @ApiResponse (
                    responseCode = "200",
                    description = "Найдена информация по идентификатору",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema (implementation = Knowledge.class)
            )
            ),


    })
    @GetMapping("{idRead}")
    public ResponseEntity<Knowledge> getKnowledgeById(@PathVariable Long idRead) {
        Knowledge knowledgeGet = service.findKnowledgeById(idRead);
        if (knowledgeGet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(knowledgeGet);
    }

    @GetMapping("all")
    public Collection<Knowledge> getAllKnowledge() {
        return service.findAllKnowledge();
    }

}