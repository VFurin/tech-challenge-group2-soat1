package com.techchallenge.adapter.mapper.api;

import com.techchallenge.adapter.driver.model.TipoPagamentoModel;
import com.techchallenge.adapter.driver.model.input.EventoPagamentoInput;
import com.techchallenge.adapter.driver.model.input.TipoPagamentoInput;
import com.techchallenge.adapter.dto.pagamentos.PagamentoPixResponseDTO;
import com.techchallenge.core.domain.entities.EventoPagamento;
import com.techchallenge.core.domain.entities.TipoPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class MercadoPagoApiMapper {

    @Autowired
    private ModelMapper mapper;

    public PagamentoPixResponseDTO toDomainObject(PagamentoPixResponseDTO input) {
        return mapper.map(input, PagamentoPixResponseDTO.class);
    }
}
