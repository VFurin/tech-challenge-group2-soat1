package com.techchallenge.adapter.mapper.api;

import java.util.Collection;
import java.util.stream.Collectors;

import com.techchallenge.adapter.driver.model.input.EventoPagamentoInput;
import com.techchallenge.core.domain.entities.EventoPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.TipoPagamentoModel;
import com.techchallenge.adapter.driver.model.input.TipoPagamentoInput;
import com.techchallenge.core.domain.entities.TipoPagamento;

@Component
public class PagamentoApiMapper {

    @Autowired
    private ModelMapper mapper;

    public TipoPagamento toDomainObject(TipoPagamentoInput input) {
        return mapper.map(input, TipoPagamento.class);
    }

    public Collection<TipoPagamentoModel> toCollectionModel(Collection<TipoPagamento> tipoPagamento) {
        return tipoPagamento.stream()
                .map(c -> mapper.map(c, TipoPagamentoModel.class))
                .collect(Collectors.toList());
    }

    public EventoPagamento toDomainObject(EventoPagamentoInput input) {
        return mapper.map(input, EventoPagamento.class);
    }
}
