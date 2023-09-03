package com.techchallenge.adapter.driver.exceptionhandler;

public class MercadoPagoException extends RuntimeException {
	private static final long serialVersionUID = -4442493329068553354L;

	public MercadoPagoException(String message) {
        super(message);
    }
}