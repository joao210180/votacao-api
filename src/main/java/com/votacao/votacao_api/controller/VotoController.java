package com.votacao.votacao_api.controller;

import com.votacao.votacao_api.dto.VotoDTO;
import com.votacao.votacao_api.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/votos")
@Tag(name = "Votação", description = "Endpoints para votar e mostrar o resultado de uma puta escolhida.")
public class VotoController {

    private  VotoService service;

    public VotoController(VotoService service) {
        this.service = service;
    }
    @Operation(summary = "Registrar um voto", description = "Registra um voto em uma sessão de votação ativa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sessão de votação não encontrada"),
            @ApiResponse(responseCode = "409", description = "Associado já votou nesta pauta")
    })
    @PostMapping("/{pautaId}")
    public ResponseEntity<Void> votar(@PathVariable Long pautaId, @RequestBody @Valid VotoDTO votoDTO) {
        service.votar(pautaId, votoDTO);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Ver resultado dos votos", description = "Mostra o resultado des votos de uma pauta escolhida.")
        @GetMapping("/{pautaId}/resultado")
        public ResponseEntity<Map<String, Long>> resultado (@PathVariable Long pautaId){
            return ResponseEntity.ok(service.contabilizarVotos(pautaId));
        }
    }

