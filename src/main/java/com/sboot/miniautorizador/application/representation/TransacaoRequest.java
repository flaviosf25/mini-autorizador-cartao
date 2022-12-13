package com.sboot.miniautorizador.application.representation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequest {
    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;

}
