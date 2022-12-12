package com.sboot.miniautorizador.application.representation;

import com.sboot.miniautorizador.domain.model.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoResponse {

    private String numeroCartao;
    private String senha;

    public static CartaoResponse fromModel(Cartao model){
        return new CartaoResponse(
                model.getNumeroCartao(),
                model.getSenha()
        );
    }
}
