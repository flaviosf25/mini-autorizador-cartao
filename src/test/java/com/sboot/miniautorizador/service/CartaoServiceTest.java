package com.sboot.miniautorizador.service;


import com.sboot.miniautorizador.application.representation.TransacaoRequest;
import com.sboot.miniautorizador.domain.model.Cartao;
import com.sboot.miniautorizador.exception.CartaoInexistenteException;
import com.sboot.miniautorizador.exception.SenhaInvalidaException;
import com.sboot.miniautorizador.infra.repositoty.CartaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void cadastrarCartaoTest() {
        Mockito.when(this.cartaoRepository.save(Mockito.any())).thenReturn(mockCartao());
        Cartao retorno = this.cartaoService.save(mockCartao());
        Assertions.assertNotNull(retorno);
    }

    @Test
    public void consultarSaldoByNumeroCartaoTest() {
        Mockito.when(this.cartaoRepository.findByNumeroCartao(Mockito.any())).thenReturn(Optional.of(mockCartao()));
        Cartao retorno = this.cartaoService.consultarSaldoByNumeroCartao("123");
        Assertions.assertNotNull(retorno);
    }

    @Test
    public void consultarSenhaCartaoInvalidaTest() throws Exception {
        Mockito.when(this.cartaoRepository.findByNumeroCartao(Mockito.any())).thenReturn(Optional.of(mockCartao()));
        var exception = Assertions.assertThrows(SenhaInvalidaException.class, () -> this.cartaoService.efetuarTransacao(mockTransacaoRequest()));
        Assertions.assertEquals("SENHA_INVALIDA", exception.getMessage());

    }

    @Test
    public void consultarSaldoByNumeroCartaoExceptionTest() throws Exception {
        Mockito.when(this.cartaoRepository.findByNumeroCartao(Mockito.any())).thenReturn(Optional.empty());
        var exception = Assertions.assertThrows(CartaoInexistenteException.class, () -> this.cartaoService.efetuarTransacao(mockTransacaoRequest()));
        Assertions.assertEquals("CARTAO_INEXISTENTE", exception.getMessage());

    }

    private Cartao mockCartao(){
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("123456");
        cartao.setSenha("1234");
        cartao.setSaldoInicial(new BigDecimal(50));
        return cartao;
    }

    private TransacaoRequest mockTransacaoRequest(){
        TransacaoRequest transacaoRequest = new TransacaoRequest();
        transacaoRequest.setNumeroCartao("12345");
        transacaoRequest.setSenhaCartao("123");
        transacaoRequest.setValor(new BigDecimal(10));
        return transacaoRequest;
    }
}
