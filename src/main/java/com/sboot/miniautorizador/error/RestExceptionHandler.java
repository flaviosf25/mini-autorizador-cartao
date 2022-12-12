package com.sboot.miniautorizador.error;

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

}
