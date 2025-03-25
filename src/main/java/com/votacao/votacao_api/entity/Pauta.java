package com.votacao.votacao_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;







@Getter
@Setter
@Entity()
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL)
    private SessaoVotacao sessao;
}
