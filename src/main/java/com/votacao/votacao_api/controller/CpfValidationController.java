package com.votacao.votacao_api.controller;

import com.votacao.votacao_api.validation.CpfValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/cpf")
@Tag(name = "Valida CPF", description = "Endpoints validar CPF de acordo com Tarefas bônus.")
public class CpfValidationController {

    private static final Random RANDOM = new Random();

    @Autowired
    private CpfValidationService cpfValidationService;

    @Operation(summary = "Ver tarefa de bonus q foi me passado", description = "Ver tarefa de bonus q foi me passado.")
    @GetMapping("/{cpf}")
    public ResponseEntity<?> checkCpf(@PathVariable String cpf) {


        if (!cpfValidationService.isCpfValido(cpf)) {
            return ResponseEntity.notFound().build();
        }

        boolean canVote = RANDOM.nextBoolean(); // Simula aleatoriedade

        return ResponseEntity.ok(new CpfResponse(canVote ? "ABLE_TO_VOTE" : "UNABLE_TO_VOTE"));
    }
    record CpfResponse(String status) {}
}
