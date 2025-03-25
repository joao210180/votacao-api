package com.votacao.votacao_api.controller;

import com.votacao.votacao_api.dto.PautaDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pautas")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody @Valid PautaDTO pautaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.criarPauta(pautaDTO));
    }
}
