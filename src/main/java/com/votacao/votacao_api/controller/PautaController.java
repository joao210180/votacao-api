package com.votacao.votacao_api.controller;

import com.votacao.votacao_api.dto.PautaDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pautas")
@Tag(name = "Pautas", description = "Endpoints para cadastrar as pautas.")
public class PautaController {

    private  PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @Operation(summary = "Criar uma nova pauta", description = "Cria uma nova pauta para votação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pauta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody @Valid PautaDTO pautaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.criarPauta(pautaDTO));
    }
}
