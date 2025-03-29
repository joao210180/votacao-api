package com.votacao.votacao_api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class SessaoVotacaoDTO {
    private Long pautaId;
    private Integer duracaoEmMinutos; // Opcional
}