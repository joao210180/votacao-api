package com.votacao.votacao_api.service;

import com.votacao.votacao_api.dto.PautaDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.repository.PautaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta criarPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaDTO.getTitulo());
        log.debug("Adding a new pauta with name [ titulo = {} ]", pautaDTO.getTitulo());
        return pautaRepository.save(pauta);
    }

    public boolean tabelaPautaNaoEstaVazia() {
        return pautaRepository.count() > 0;
    }

}
