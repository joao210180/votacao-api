package com.votacao.votacao_api.dataloader;

import com.votacao.votacao_api.dto.PautaDTO;
import com.votacao.votacao_api.dto.SessaoVotacaoDTO;
import com.votacao.votacao_api.dto.VotoDTO;
import com.votacao.votacao_api.service.PautaService;
import com.votacao.votacao_api.service.SessaoVotacaoService;
import com.votacao.votacao_api.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Profile("data-load")
@Slf4j
@Component
public class DbLoader implements CommandLineRunner {
    @Autowired
    PautaService pautaService;
    @Autowired
    SessaoVotacaoService sessaoVotacaoService;
    @Autowired
    VotoService votoService;


    @Override
    public void run(String... args) throws Exception {


        log.info("Popular com pauta caso nao exite");

        if (pautaService.seExistem().isEmpty()) {

            //cadastra pauta
            for (int i = 1; i < 11; i++) {
                PautaDTO pauta = new PautaDTO("Pauta " + i);
                pautaService.criarPauta(pauta);
            }
            //abre sessao
            SessaoVotacaoDTO dto = new SessaoVotacaoDTO(1L, 5);
            sessaoVotacaoService.abrirSessao(dto);
            //votar
            VotoDTO votoDTO = new VotoDTO(1212L, true);
            votoService.votar(1L, votoDTO);
            //Ve resultado
            votoService.contabilizarVotos(1L);
        }

    }
}
