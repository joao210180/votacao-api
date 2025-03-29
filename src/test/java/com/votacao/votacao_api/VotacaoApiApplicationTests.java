package com.votacao.votacao_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local") // perfil de teste para configurar o H2
class VotacaoApiApplicationTests {
    @Test
    void contextLoads() {
    }
}
