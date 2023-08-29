package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.adapter.mapper.business.PedidoBusinessMapper;
import com.techchallenge.adapter.mapper.db.PedidoEntityMapper;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.StatusPedido;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.drivers.db.entities.PedidoEntity;
import com.techchallenge.drivers.db.repositories.PedidoRepository;

@Component
public class PedidoGatewayImpl implements PedidoGateway {

	private static final String MSG_PEDIDO_EM_USO = "Pedido em uso com o id %d";
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado com o id %d";
	private static final String MSG_PEDIDO_STATUS_NAO_ENCONTRADO = "Não existe pedido com o id %d e com o status %s";
	
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private PedidoEntityMapper mapper;
    @Autowired
    private PedidoBusinessMapper businessMapper;
    
    public List<Pedido> buscarPedidos() {
        return businessMapper.toCollectionModel(repository.findAll());
    }

    public Pedido buscarPedidoPorId(Long id) {
        PedidoEntity entity = repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PEDIDO_NAO_ENCONTRADO, id)));
        
    	return businessMapper.toModel(entity);
    }

    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return businessMapper.toCollectionModel(repository.findByStatus(statusPedido));
    }
    
    public Pedido buscarPedidoPorIdEStatus(Long id, StatusPedido statusPedido) {
        PedidoEntity entity = repository.findByIdAndStatus(id, statusPedido).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, id, statusPedido.name())));
        
    	return businessMapper.toModel(entity);
    }

    @Transactional
    public void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido) {
    	PedidoEntity entity = mapper.toModel(pedido);
        entity.setStatus(statusPedido);
        repository.save(entity);
    }
    
    @Transactional
    public void atualizar(Pedido pedido) {
    	PedidoEntity entity = mapper.toModel(pedido);
    	repository.save(entity);
    }
    
    @Transactional
    public void excluir(Long pedidoId) {
    	try {
    		repository.deleteById(pedidoId);
    		repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PEDIDO_EM_USO, pedidoId));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_NAO_ENCONTRADO, pedidoId));
		}
    }
}