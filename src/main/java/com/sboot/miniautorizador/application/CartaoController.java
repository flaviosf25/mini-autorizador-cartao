package com.sboot.miniautorizador.application;

import com.sboot.miniautorizador.application.representation.CartaoRequest;
import com.sboot.miniautorizador.application.representation.CartaoResponse;
import com.sboot.miniautorizador.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping("cartoes")
    public ResponseEntity cadastrarCartao(@RequestBody CartaoRequest cartaoRequest){
        return new ResponseEntity(CartaoResponse.fromModel(cartaoService.save(cartaoRequest.toModel())), HttpStatus.CREATED);
    }

}
