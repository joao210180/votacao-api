package com.votacao.votacao_api.dataloader;
import com.votacao.votacao_api.dto.PautaDTO;
import com.votacao.votacao_api.service.PautaService;
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

    @Override
    public void run(String... args) throws Exception {


        log.info("Populating db with pauta");

        for(int i =1; i<11; i++){
            PautaDTO pauta = new PautaDTO();
           // pauta.setTitulo("Pauta  ");

            pautaService.criarPauta(pauta);

        }




    }
}
