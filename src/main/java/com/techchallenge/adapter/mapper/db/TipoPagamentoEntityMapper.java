package com.techchallenge.adapter.mapper.db;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.core.domain.entities.TipoPagamento;
import com.techchallenge.drivers.db.entities.TipoPagamentoEntity;

@Component
public class TipoPagamentoEntityMapper {

	@Autowired
	private ModelMapper mapper;
	
	public TipoPagamentoEntity toModel(TipoPagamento tipo) {
		return mapper.map(tipo, TipoPagamentoEntity.class);
	}
	
	public List<TipoPagamentoEntity> toCollectionModel(Collection<TipoPagamento> tipoPagamentos) {
		return tipoPagamentos.stream()
				.map(c -> mapper.map(c, TipoPagamentoEntity.class))
				.collect(Collectors.toList());
	}
}
