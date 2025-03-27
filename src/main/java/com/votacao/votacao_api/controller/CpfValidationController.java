package com.votacao.votacao_api.controller;

import com.votacao.votacao_api.validation.CpfValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/cpf")
public class CpfValidationController {

    private static final Random RANDOM = new Random();

    @Autowired
    private CpfValidationService cpfValidationService;

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
