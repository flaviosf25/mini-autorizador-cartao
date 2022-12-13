package com.sboot.miniautorizador.application;


import com.sboot.miniautorizador.application.representation.TransacaoRequest;
import com.sboot.miniautorizador.exception.CartaoInexistenteException;
import com.sboot.miniautorizador.exception.SenhaInvalidaException;
import com.sboot.miniautorizador.application.representation.CartaoRequest;
import com.sboot.miniautorizador.domain.model.Cartao;
import com.sboot.miniautorizador.service.CartaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public class CartaoControllerTest {

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private CartaoController cartaoControler;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void cadastrarCartaoTest() {
        Mockito.when(this.cartaoService.save(Mockito.any())).thenReturn(mockCartao());
        ResponseEntity retorno = cartaoControler.cadastrarCartao(mockCartaoRequest());
        Assertions.assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
    }

    @Test
    public void getSaldoCartaoTest() {
        Mockito.when(this.cartaoService.consultarSaldoByNumeroCartao(Mockito.any())).thenReturn(mockCartao());
        BigDecimal retorno = cartaoControler.getSaldoCartao("123");
        Assertions.assertEquals(mockCartao().getSaldoInicial(), retorno);
    }

    @Test
    public void consultarSenhaCartaoInvalidaTest() throws Exception {
        Mockito.when(this.cartaoService.efetuarTransacao(Mockito.any())).thenThrow(new SenhaInvalidaException("Teste"));
        var exception = Assertions.assertThrows(SenhaInvalidaException.class, () -> this.cartaoControler.efetuarTransacao(mockTransacaoRequest()));
        Assertions.assertEquals("Teste", exception.getMessage());

    }

    @Test
    public void consultarSaldoByNumeroCartaoTest() throws Exception {
        Mockito.when(this.cartaoService.efetuarTransacao(Mockito.any())).thenThrow(new CartaoInexistenteException("Teste"));
        var exception = Assertions.assertThrows(CartaoInexistenteException.class, () -> this.cartaoControler.efetuarTransacao(mockTransacaoRequest()));
        Assertions.assertEquals("Teste", exception.getMessage());

    }

    private Cartao mockCartao(){
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("12345");
        cartao.setSenha("123");
        return cartao;
    }

    private CartaoRequest mockCartaoRequest(){
        CartaoRequest cartaoRequest = new CartaoRequest();
        cartaoRequest.setNumeroCartao("12345");
        cartaoRequest.setSenha("123");
        return cartaoRequest;
    }

    private TransacaoRequest mockTransacaoRequest(){
        TransacaoRequest transacaoRequest = new TransacaoRequest();
        transacaoRequest.setNumeroCartao("12345");
        transacaoRequest.setSenhaCartao("123");
        return transacaoRequest;
    }
}
