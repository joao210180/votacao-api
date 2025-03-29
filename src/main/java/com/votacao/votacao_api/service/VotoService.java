package com.votacao.votacao_api.service;


import com.votacao.votacao_api.dto.VotoDTO;
import com.votacao.votacao_api.entity.SessaoVotacao;
import com.votacao.votacao_api.entity.Voto;
import com.votacao.votacao_api.repository.SessaoVotacaoRepository;
import com.votacao.votacao_api.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class VotoService {
    private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public VotoService(VotoRepository votoRepository, SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.votoRepository = votoRepository;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    public void votar(Long pautaId, VotoDTO votoDTO) {
        SessaoVotacao sessao = sessaoVotacaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new RuntimeException("Sessão de votação não encontrada"));

        if (LocalDateTime.now().isAfter(sessao.getFim())) {
            throw new RuntimeException("Sessão de votação encerrada");
        }

        if (votoRepository.existsByPautaIdAndAssociadoId(pautaId, votoDTO.getAssociadoId())) {
            throw new RuntimeException("Associado já votou nesta pauta");
        }

        Voto voto = new Voto(null, votoDTO.getAssociadoId(), votoDTO.getVoto(), sessao.getPauta());
        votoRepository.save(voto);
    }

    public Map<String, Long> contabilizarVotos(Long pautaId) {
        List<Voto> votos = votoRepository.findByPautaId(pautaId);
        long sim = votos.stream().filter(Voto::getVoto).count();
        long nao = votos.size() - sim;

        Map<String, Long> resultado = new HashMap<>();
        resultado.put("Sim", sim);
        resultado.put("Não", nao);
        return resultado;
    }
}