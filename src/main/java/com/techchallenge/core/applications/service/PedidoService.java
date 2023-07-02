package com.techchallenge.core.applications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.StatusPedido;
import com.techchallenge.core.domain.exception.EntidadeEmUsoException;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;

@Service
public class PedidoService {

	private static final String MSG_PEDIDO_EM_USO = "Pedido em uso com o id %d";
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado com o id %d";
	private static final String MSG_PEDIDO_STATUS_NAO_ENCONTRADO = "Não existe pedido com o id %d e com o status %s";
	
    @Autowired
    private PedidoRepository repository;
    
    public List<Pedido> buscarPedidos() {
        return repository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PEDIDO_NAO_ENCONTRADO, id)));
    }

    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return repository.findByStatus(statusPedido);
    }
    
    public Pedido buscarPedidoPorIdEStatus(Long id, StatusPedido statusPedido) {
        return repository.findByIdAndStatus(id, statusPedido).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, id, statusPedido.name())));
    }

    @Transactional
    public void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido) {
        pedido.setStatus(statusPedido);
        repository.save(pedido);
    }
    
    @Transactional
    public void atualizar(Pedido pedido) {
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