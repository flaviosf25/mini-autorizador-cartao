package com.sboot.miniautorizador.service;


import com.sboot.miniautorizador.application.representation.TransacaoRequest;
import com.sboot.miniautorizador.domain.model.Cartao;
import com.sboot.miniautorizador.exception.CartaoInexistenteException;
import com.sboot.miniautorizador.exception.SaldoInsuficienteException;
import com.sboot.miniautorizador.exception.SenhaInvalidaException;
import com.sboot.miniautorizador.infra.repositoty.CartaoRepository;
import com.sboot.miniautorizador.util.Constantes;
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

    public Cartao efetuarTransacao(TransacaoRequest transacaoRequest) throws Exception {

        Optional<Cartao> retornoCartao = this.repository.findByNumeroCartao(transacaoRequest.getNumeroCartao());

        retornoCartao.orElseThrow(() -> new CartaoInexistenteException(Constantes.CARTAO_INEXISTENTE));

        retornoCartao.filter(cartao -> cartao.getSenha().equals(transacaoRequest.getSenhaCartao()))
                .orElseThrow(() -> new SenhaInvalidaException(Constantes.SENHA_INVALIDA));

        retornoCartao.filter(cartao -> cartao.getSaldoInicial().compareTo(transacaoRequest.getValor()) == 1)
                .orElseThrow(() -> new SaldoInsuficienteException(Constantes.SALDO_INSUFICIENTE));

        retornoCartao.get().setSaldoInicial(retornoCartao.get().getSaldoInicial().subtract(transacaoRequest.getValor()));
        return this.repository.save(retornoCartao.get());
    }
}
