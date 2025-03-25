package com.votacao.votacao_api.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessaoVotacaoDTO {
    private Long pautaId;
    private Integer duracaoEmMinutos; // Opcional
}