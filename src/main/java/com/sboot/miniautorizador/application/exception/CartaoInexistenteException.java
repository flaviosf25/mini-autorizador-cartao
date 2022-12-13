package com.sboot.miniautorizador.application.exception;

public class CartaoInexistenteException extends Exception{

    public CartaoInexistenteException(String msg) {
        super(msg);
    }
}
