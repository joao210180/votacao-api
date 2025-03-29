package com.votacao.service;

import com.votacao.votacao_api.VotacaoApiApplication;
import com.votacao.votacao_api.dto.SessaoVotacaoDTO;
import com.votacao.votacao_api.dto.VotoDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.entity.SessaoVotacao;
import com.votacao.votacao_api.repository.PautaRepository;
import com.votacao.votacao_api.repository.VotoRepository;
import com.votacao.votacao_api.service.SessaoVotacaoService;
import com.votacao.votacao_api.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VotacaoApiApplication.class)
@ActiveProfiles("local") // Usa o perfil de teste para rodar com H2
@Transactional // Garante rollback automático após cada teste
public class VotoIntegrationTest {

    @Autowired
    private VotoService votoService;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    private Pauta pauta;

    @BeforeEach
    public void setup() {
        votoRepository.deleteAll(); // Limpa os votos antes de iniciar
        pautaRepository.deleteAll(); // Limpa pautas antes de iniciar

        pauta = new Pauta();
        pauta.setTitulo("Pauta de Teste");
        pautaRepository.save(pauta);

        SessaoVotacaoDTO dto = new SessaoVotacaoDTO(pauta.getId(), null);
        sessaoVotacaoService.abrirSessao(dto); // Abre sessão com tempo padrão (1 min)
    }

    @Test
    public void testVotoComSucesso() {
        // Dado
        VotoDTO votoDTO = new VotoDTO(111L, true); // Votando a favor

        // Quando
        votoService.votar(pauta.getId(), votoDTO);

        // Então
        assertNotNull(votoDTO);
        assertEquals(111L, votoDTO.getAssociadoId());
        assertTrue(votoDTO.getVoto());

        // Verifica se o voto foi salvo corretamente
        assertTrue(votoRepository.existsByPautaIdAndAssociadoId(pauta.getId(), 111L));
    }

    @Test
    public void testVotoComSessaoNaoEncontrada() {
        // Dado
        VotoDTO votoDTO = new VotoDTO(222L, false); // Votando contra

        // Quando
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            votoService.votar(999L, votoDTO); // ID de pauta inexistente
        });

        // Então
        assertEquals("Sessão de votação não encontrada", exception.getMessage());
    }

    @Test
    public void testVotoComSessaoEncerrada() {
        // Criar Pauta
        Pauta novaPauta = new Pauta();
        novaPauta.setTitulo("Pauta Encerrada");
        pautaRepository.save(novaPauta);

        // Criar Sessão com duração negativa (já encerrada)
        SessaoVotacaoDTO dto = new SessaoVotacaoDTO(novaPauta.getId(), -3);
        sessaoVotacaoService.abrirSessao(dto);

        // Criar VotoDTO
        VotoDTO votoDTO = new VotoDTO(333L, true); // Votando a favor

        // Quando - Tentar votar em uma sessão encerrada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            votoService.votar(novaPauta.getId(), votoDTO);
        });

        // Então
        assertEquals("Sessão de votação encerrada", exception.getMessage());
    }

    @Test
    public void testVotoRepeticaoDeAssociado() {
        // Dado
        VotoDTO votoDTO = new VotoDTO(444L, true); // Votando a favor

        // Primeiro voto é registrado com sucesso
        votoService.votar(pauta.getId(), votoDTO);

        // Quando - Tentativa de votar novamente
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            votoService.votar(pauta.getId(), votoDTO); // Mesmo associado tentando votar novamente
        });

        // Então
        assertEquals("Associado já votou nesta pauta", exception.getMessage());
    }
}
