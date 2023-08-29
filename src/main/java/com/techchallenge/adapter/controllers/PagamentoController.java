package com.techchallenge.adapter.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.TipoPagamentoModel;
import com.techchallenge.adapter.driver.model.input.TipoPagamentoInput;
import com.techchallenge.adapter.mapper.api.PagamentoApiMapper;
import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.core.domain.usecases.PagamentoUseCase;

@Component
public class PagamentoController {
	
    @Autowired
    private PagamentoUseCase useCase;
    
    @Autowired
    private PagamentoApiMapper mapper;
	
	public void realizarPagamento(Long pedidoId, TipoPagamentoInput tipoPagamentoInput) {
		try {
			Thread.sleep(5000);

			TipoPagamento tipoPagamento = mapper.toDomainObject(tipoPagamentoInput);
			useCase.efetuarPagamento(pedidoId, tipoPagamento);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Collection<TipoPagamentoModel> listar() {
		return mapper.toCollectionModel(useCase.listar());
	}
}