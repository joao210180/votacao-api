package com.votacao.votacao_api.controller;
import com.votacao.votacao_api.dto.SessaoVotacaoDTO;
import com.votacao.votacao_api.entity.SessaoVotacao;
import com.votacao.votacao_api.service.SessaoVotacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessoes")
public class SessaoVotacaoController {

    private  SessaoVotacaoService service;

    public SessaoVotacaoController(SessaoVotacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SessaoVotacao> abrirSessao(@RequestBody @Valid SessaoVotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.abrirSessao(dto));
    }
}
