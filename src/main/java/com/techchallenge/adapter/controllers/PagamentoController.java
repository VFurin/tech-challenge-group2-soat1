package com.techchallenge.adapter.controllers;

import java.util.Collection;

import com.techchallenge.adapter.driver.model.input.EventoPagamentoInput;
import com.techchallenge.adapter.dto.pagamentos.PagamentoPixResponseDTO;
import com.techchallenge.core.domain.entities.EventoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.TipoPagamentoModel;
import com.techchallenge.adapter.driver.model.input.TipoPagamentoInput;
import com.techchallenge.adapter.mapper.api.PagamentoApiMapper;
import com.techchallenge.adapter.mapper.api.MercadoPagoApiMapper;
import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.core.domain.usecases.PagamentoUseCase;

@Component
public class PagamentoController {
	
    @Autowired
    private PagamentoUseCase useCase;
    
    @Autowired
    private PagamentoApiMapper mapper;

	@Autowired
	private MercadoPagoApiMapper mercadoPagoApiMapper;
	
	public PagamentoPixResponseDTO realizarPagamento(Long pedidoId, TipoPagamentoInput tipoPagamentoInput) {
		TipoPagamento tipoPagamento = mapper.toDomainObject(tipoPagamentoInput);

		return mercadoPagoApiMapper.toDomainObject(useCase.efetuarPagamento(pedidoId, tipoPagamento));
	}
	
	public Collection<TipoPagamentoModel> listar() {
		return mapper.toCollectionModel(useCase.listar());
	}

	public void confirmarPagamento(Long pedidoId, EventoPagamentoInput eventoPagamentoInput) {
		try {
			Thread.sleep(5000);
			EventoPagamento eventoPagamento = mapper.toDomainObject(eventoPagamentoInput);
			useCase.confirmarPagamento(pedidoId, eventoPagamento);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}