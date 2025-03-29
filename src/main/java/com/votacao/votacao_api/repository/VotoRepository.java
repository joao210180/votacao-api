package com.votacao.votacao_api.repository;


import com.votacao.votacao_api.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByPautaIdAndAssociadoId(Long pautaId, Long associadoId);
    List<Voto> findByPautaId(Long pautaId);
}