package com.sboot.miniautorizador.error;

import com.sboot.miniautorizador.application.exception.CartaoInexistenteException;
import com.sboot.miniautorizador.application.exception.SaldoInsuficienteException;
import com.sboot.miniautorizador.application.exception.SenhaInvalidaException;
import com.sboot.miniautorizador.util.Constantes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity erroCartaoJaExiste(DataIntegrityViolationException ex){
        ErrorResponse errorResponse = new ErrorResponse(Constantes.CARTAO_JA_EXISTE, HttpStatus.UNPROCESSABLE_ENTITY.value());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity erroCartaoNaoEncontrato(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    protected ResponseEntity erroSaldoInsuficiente(SaldoInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    protected ResponseEntity erroSenhaInvalida(SenhaInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(CartaoInexistenteException.class)
    protected ResponseEntity erroCartaoInxistente(CartaoInexistenteException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

}
