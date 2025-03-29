package com.votacao.service;

import com.votacao.votacao_api.VotacaoApiApplication;
import com.votacao.votacao_api.dto.PautaDTO;
import com.votacao.votacao_api.entity.Pauta;
import com.votacao.votacao_api.repository.PautaRepository;
import com.votacao.votacao_api.service.PautaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VotacaoApiApplication.class)
@ActiveProfiles("local") // perfil de teste para configurar o H2
public class PautaIntegrationTest {

    @Autowired
    private PautaService pautaService;

    @Autowired
    private PautaRepository pautaRepository;


    @Test
    public void testPautaCriadaComSuceso() {
        // Dado
        Pauta pauta = new Pauta();
        pauta.setTitulo("Pauta de Teste");

        // Quando
        pautaRepository.save(pauta);

        // Então
        assertNotNull(pauta);
        assertEquals(pauta.getId(),1L);
        assertNotNull(pauta.getTitulo());
        assertNull(pauta.getSessao());

        Pauta pautaAux  = pautaRepository.findById(pauta.getId()).orElse(null);
        assertNotNull(pautaAux);
        assertEquals(pautaAux.getId(), pauta.getId());
    }

    @Test
    public void testPautaCriadaSemTitulo() {
        // Dado
        PautaDTO pautaDTO = new PautaDTO("");

        // Quando
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pautaService.criarPauta(pautaDTO);
        });

        // Então
        assertEquals("Titulo da Pauta nao pode ser vazio ou null", exception.getMessage());
    }

    @Test
    public void testPautaCriadaComTituloNull() {
        // Dado
        PautaDTO pautaDTO = new PautaDTO(null);

        // Quando
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pautaService.criarPauta(pautaDTO);
        });

        // Então
        assertEquals("Titulo da Pauta nao pode ser vazio ou null", exception.getMessage());
    }


}