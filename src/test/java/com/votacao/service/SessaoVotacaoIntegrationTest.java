package com.votacao.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.votacao.votacao_api.VotacaoApiApplication;
import com.votacao.votacao_api.dto.SessaoVotacaoDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.entity.SessaoVotacao;
import com.votacao.votacao_api.repository.PautaRepository;
import com.votacao.votacao_api.repository.SessaoVotacaoRepository;
import com.votacao.votacao_api.service.SessaoVotacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest(classes = VotacaoApiApplication.class)
@ActiveProfiles("local") // Use um perfil de teste para configurar o H2
public class SessaoVotacaoIntegrationTest {

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Test
    public void testAbrirSessaoSucessoComDuracaoEspecificada() {
        // Dado
        Pauta pauta = new Pauta();
        pauta.setTitulo("Pauta de Teste");
        pautaRepository.save(pauta);

        SessaoVotacaoDTO dto = new SessaoVotacaoDTO(pauta.getId(),2);

        // Quando
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrirSessao(dto);

        // Então
        assertNotNull(sessaoVotacao);
        assertEquals(pauta.getId(), sessaoVotacao.getPauta().getId());
        assertNotNull(sessaoVotacao.getInicio());
        assertNotNull(sessaoVotacao.getFim());
        assertEquals(2, sessaoVotacao.getFim().getMinute() - sessaoVotacao.getInicio().getMinute());

        SessaoVotacao sessaoSalva = sessaoVotacaoRepository.findById(sessaoVotacao.getId()).orElse(null);
        assertNotNull(sessaoSalva);
        assertEquals(sessaoVotacao.getPauta().getId(), sessaoSalva.getPauta().getId());
    }

    @Test
    public void testAbrirSessaoPautaNaoEncontrada() {
        // Dado
        SessaoVotacaoDTO dto = new SessaoVotacaoDTO(5L, null);

        // Quando
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sessaoVotacaoService.abrirSessao(dto);
        });

        // Então
        assertEquals("Pauta não encontrada", exception.getMessage());
    }

    @Test
    public void testAbrirSessaoDuracaoPadrao() {
        // Dado
        Pauta pauta = new Pauta();
        pauta.setTitulo("Pauta de Teste");
        pautaRepository.save(pauta);

        SessaoVotacaoDTO dto = new SessaoVotacaoDTO(pauta.getId(),null);

        // Quando
        SessaoVotacao sessaoVotacao = sessaoVotacaoService.abrirSessao(dto);

        // Então
        assertNotNull(sessaoVotacao);
        assertEquals(pauta.getId(), sessaoVotacao.getPauta().getId());
        assertNotNull(sessaoVotacao.getInicio());
        assertNotNull(sessaoVotacao.getFim());
        assertEquals(1, sessaoVotacao.getFim().getMinute() - sessaoVotacao.getInicio().getMinute());

        SessaoVotacao sessaoSalva = sessaoVotacaoRepository.findById(sessaoVotacao.getId()).orElse(null);
        assertNotNull(sessaoSalva);
        assertEquals(sessaoVotacao.getPauta().getId(), sessaoSalva.getPauta().getId());
    }
}