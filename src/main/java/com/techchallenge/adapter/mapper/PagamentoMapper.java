package com.techchallenge.adapter.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.driver.model.TipoPagamentoModel;
import com.techchallenge.adapter.driver.model.input.TipoPagamentoInput;
import com.techchallenge.core.domain.TipoPagamento;

@Component
public class PagamentoMapper {

    @Autowired
    private ModelMapper mapper;

    public TipoPagamento toDomainObject(TipoPagamentoInput input) {
        TipoPagamento tipoPagamento = mapper.map(input, TipoPagamento.class);
        
        return tipoPagamento;
    }
    
    public Collection<TipoPagamentoModel> toCollectionModel(Collection<TipoPagamento> tipoPagamento) {
        return tipoPagamento.stream()
                .map(c -> mapper.map(c, TipoPagamentoModel.class))
                .collect(Collectors.toList());
    }
}
