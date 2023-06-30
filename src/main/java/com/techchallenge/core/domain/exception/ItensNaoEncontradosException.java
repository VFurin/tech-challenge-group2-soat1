package com.techchallenge.core.domain.exception;

public class ItensNaoEncontradosException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_ITENS_PEDIDO_NAO_ENCONTRADO = "Não foram encontrados itens no pedido %d";

	public ItensNaoEncontradosException(String mensagem) {
		super(mensagem);
	}

	public ItensNaoEncontradosException(Long pedidoId) {
		this(String.format(MSG_ITENS_PEDIDO_NAO_ENCONTRADO, pedidoId));
	}
}