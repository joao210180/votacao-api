package com.votacao.votacao_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long associadoId;
    private Boolean voto; // true = Sim, false = Não

    @ManyToOne
    private Pauta pauta;
}
