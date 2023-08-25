package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.PedidoGateway;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.StatusPedido;

//@Service
public class PedidoUseCase {

	private static final String MSG_PEDIDO_EM_USO = "Pedido em uso com o id %d";
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado com o id %d";
	private static final String MSG_PEDIDO_STATUS_NAO_ENCONTRADO = "Não existe pedido com o id %d e com o status %s";

	@Autowired
    private PedidoGateway gateway;
    
    public List<Pedido> buscarPedidos() {
//        return repository.buscarPedidos();
    	return null;
    }

    public Pedido buscarPedidoPorId(Long id) {
//        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
//                String.format(MSG_PEDIDO_NAO_ENCONTRADO, id)));
    	
    	return null;
    }

    public List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido) {
//        return repository.findByStatus(statusPedido);
    	return null;
    }
    
    public Pedido buscarPedidoPorIdEStatus(Long id, StatusPedido statusPedido) {
//        return repository.findByIdAndStatus(id, statusPedido).orElseThrow(() -> new EntidadeNaoEncontradaException(
//                String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, id, statusPedido.name())));
    	
    	return null;
    }

    public void atualizarStatusDoPedido(Pedido pedido, StatusPedido statusPedido) {
//        pedido.setStatus(statusPedido);
//        repository.save(pedido);
    }
    
    public void atualizar(Pedido pedido) {
//    	repository.save(pedido);
    }
    
    public void excluir(Long pedidoId) {
//    	try {
//    		repository.deleteById(pedidoId);
//    		repository.flush();
//		} catch (DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(String.format(MSG_PEDIDO_EM_USO, pedidoId));
//		} catch (EmptyResultDataAccessException e) {
//			throw new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_NAO_ENCONTRADO, pedidoId));
//		}
    }
}