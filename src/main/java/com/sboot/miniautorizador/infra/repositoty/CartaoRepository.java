package com.sboot.miniautorizador.infra.repositoty;

import com.sboot.miniautorizador.domain.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
