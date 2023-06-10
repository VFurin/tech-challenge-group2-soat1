package com.techchallenge.adapter.driver.exceptionhandler;

import org.springframework.validation.BindingResult;

public class ValidacaoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ValidacaoException(BindingResult binding) {
		this.bindingResult = binding;
	}
	
	private BindingResult bindingResult;
	
	public BindingResult getBindingResult() {
		return this.bindingResult;
	}
}
