package com.techchallenge.adapter.driver.exceptionhandler;

public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
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
	
	public String getTitle() {
		return this.title;
	}
	
	public String getUri() {
		return this.uri;
	}
}
