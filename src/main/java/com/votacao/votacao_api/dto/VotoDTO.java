package com.votacao.votacao_api.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VotoDTO {
    private Long associadoId;
    private Boolean voto;
}