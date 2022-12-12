package com.sboot.miniautorizador.service;

import com.sboot.miniautorizador.domain.model.Cartao;
import com.sboot.miniautorizador.infra.repositoty.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return Optional.of(repository.save(cartao)).orElseThrow();
    }

    public Cartao consultarSaldoByNumeroCartao(String numeroCartao) {
        return Optional.of(repository.findByNumeroCartao(numeroCartao)).get().orElseThrow(() -> new NotFoundException(""));
    }
}
