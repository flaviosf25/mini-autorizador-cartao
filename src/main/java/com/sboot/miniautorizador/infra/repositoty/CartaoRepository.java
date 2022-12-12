package com.sboot.miniautorizador.infra.repositoty;

import com.sboot.miniautorizador.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNumeroCartao(String numeroCartao);
}
