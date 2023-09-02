package com.techchallenge.adapter.mapper.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techchallenge.adapter.dto.pagamentos.PagamentoPixResponseDTO;
import com.techchallenge.adapter.dto.pagamentos.PagamentoResponseDTO;

@Component
public class MercadoPagoApiMapper {

    @Autowired
    private ModelMapper mapper;

    public PagamentoPixResponseDTO toDomainObject(PagamentoPixResponseDTO input) {
        return mapper.map(input, PagamentoPixResponseDTO.class);
    }
    
    public PagamentoResponseDTO toDomainObject(PagamentoResponseDTO input) {
        return mapper.map(input, PagamentoResponseDTO.class);
    }
}
