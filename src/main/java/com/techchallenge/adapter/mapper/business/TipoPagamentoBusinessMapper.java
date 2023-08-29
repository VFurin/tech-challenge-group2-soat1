package com.techchallenge.adapter.mapper.business;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.drivers.db.entities.TipoPagamentoEntity;

@Component
public class TipoPagamentoBusinessMapper {

	@Autowired
	private ModelMapper mapper;
	
	public TipoPagamento toModel(TipoPagamentoEntity entity) {
		return mapper.map(entity, TipoPagamento.class);
	}
	
	public List<TipoPagamento> toCollectionModel(Collection<TipoPagamentoEntity> tipoPagamentos) {
		return tipoPagamentos.stream()
				.map(c -> mapper.map(c, TipoPagamento.class))
				.collect(Collectors.toList());
	}
}
