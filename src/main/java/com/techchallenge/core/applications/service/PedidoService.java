package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.drivers.db.entities.PedidoEntity;
import com.techchallenge.drivers.db.entities.StatusPedidoEntity;
import com.techchallenge.drivers.db.repositories.PedidoRepository;

//@Service
public class PedidoService {

	private static final String MSG_PEDIDO_EM_USO = "Pedido em uso com o id %d";
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado com o id %d";
	private static final String MSG_PEDIDO_STATUS_NAO_ENCONTRADO = "Não existe pedido com o id %d e com o status %s";
	
    @Autowired
    private PedidoRepository repository;
    
    public List<PedidoEntity> buscarPedidos() {
        return repository.findAll();
    }

    public PedidoEntity buscarPedidoPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PEDIDO_NAO_ENCONTRADO, id)));
    }

    public List<PedidoEntity> buscarPedidosPorStatus(StatusPedidoEntity statusPedido) {
        return repository.findByStatus(statusPedido);
    }
    
    public PedidoEntity buscarPedidoPorIdEStatus(Long id, StatusPedidoEntity statusPedido) {
        return repository.findByIdAndStatus(id, statusPedido).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, id, statusPedido.name())));
    }

    @Transactional
    public void atualizarStatusDoPedido(PedidoEntity pedido, StatusPedidoEntity statusPedido) {
        pedido.setStatus(statusPedido);
        repository.save(pedido);
    }
    
    @Transactional
    public void atualizar(PedidoEntity pedido) {
    	repository.save(pedido);
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