package com.votacao.votacao_api.service;

import com.votacao.votacao_api.dto.SessaoVotacaoDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.entity.SessaoVotacao;
import com.votacao.votacao_api.repository.PautaRepository;
import com.votacao.votacao_api.repository.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
public class SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaRepository pautaRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public SessaoVotacao abrirSessao(SessaoVotacaoDTO dto) {
        Pauta pauta = pautaRepository.findById(dto.getPautaId()).orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusMinutes(dto.getDuracaoEmMinutos() != null ? dto.getDuracaoEmMinutos() : 1);

        SessaoVotacao sessao = new SessaoVotacao(null, pauta, inicio, fim);
        log.debug("Adding a new sessao  ");
        return sessaoVotacaoRepository.save(sessao);
    }
}
