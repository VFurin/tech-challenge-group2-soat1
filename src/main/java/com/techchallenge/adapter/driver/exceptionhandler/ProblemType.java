package com.techchallenge.adapter.driver.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCONSISTENTE("/mensagem-inconsistente", "Mensagem inconsistente"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");
	
	private String title;
	private String uri;
	
	private static final String BASE_URL = "http://localhost";
	
	ProblemType(String path, String title) {
		this.uri = BASE_URL + path;
		this.title = title;
	}
}
