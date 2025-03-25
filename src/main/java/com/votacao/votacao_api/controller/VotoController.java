package com.votacao.votacao_api.controller;
import com.votacao.votacao_api.dto.VotoDTO;
import com.votacao.votacao_api.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/votos")
public class VotoController {
    private final VotoService service;

    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping("/{pautaId}")
    public ResponseEntity<Void> votar(@PathVariable Long pautaId, @RequestBody @Valid VotoDTO votoDTO) {
        service.votar(pautaId, votoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{pautaId}/resultado")
    public ResponseEntity<Map<String, Long>> resultado(@PathVariable Long pautaId) {
        return ResponseEntity.ok(service.contabilizarVotos(pautaId));
    }
}
