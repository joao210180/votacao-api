package com.votacao.votacao_api.repository;



import com.votacao.votacao_api.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {}