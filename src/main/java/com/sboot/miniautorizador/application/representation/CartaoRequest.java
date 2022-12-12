package com.sboot.miniautorizador.application.representation;

import com.sboot.miniautorizador.domain.model.Cartao;
import lombok.Data;

@Data
public class CartaoRequest {
    private String numeroCartao;
    private String senha;

    public Cartao toModel(){
        return new Cartao(numeroCartao, senha);
    }
}
