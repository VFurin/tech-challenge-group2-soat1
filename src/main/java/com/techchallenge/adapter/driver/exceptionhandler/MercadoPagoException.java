package com.techchallenge.adapter.driver.exceptionhandler;

public class MercadoPagoException extends RuntimeException {
    public MercadoPagoException(String message) {
        super(message);
    }
}