package com.votacao.votacao_api.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VotoDTO {
    private Long associadoId;
    private Boolean voto;
}