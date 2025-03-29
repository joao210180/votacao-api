package com.votacao.votacao_api.controller;
import com.votacao.votacao_api.dto.SessaoVotacaoDTO;
import com.votacao.votacao_api.entity.SessaoVotacao;
import com.votacao.votacao_api.service.SessaoVotacaoService;
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
@RequestMapping("/api/v1/sessoes")
@Tag(name = "Sessão de votos", description = "Endpoints abrir sessão para determinada pauta.")
public class SessaoVotacaoController {

    private  SessaoVotacaoService service;

    public SessaoVotacaoController(SessaoVotacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Abrir uma sessão de votação", description = "Inicia uma sessão para uma pauta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão aberta com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada")
    })
    @PostMapping
    public ResponseEntity<SessaoVotacao> abrirSessao(@RequestBody @Valid SessaoVotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.abrirSessao(dto));
    }
}
