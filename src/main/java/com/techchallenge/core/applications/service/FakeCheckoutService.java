package com.techchallenge.core.applications.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.core.applications.ports.ClienteRepository;
import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.applications.ports.ProdutoRepository;
import com.techchallenge.core.domain.Cliente;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.Produto;
import com.techchallenge.core.domain.StatusPedido;

@Service
public class FakeCheckoutService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Pedido checkout(Pedido pedido) {
		
		this.validarItens(pedido);
		this.validarCliente(pedido);
		
		pedido.setDataSolicitacao(OffsetDateTime.now());
		pedido.setStatus(StatusPedido.RECEBIDO);
		pedido.calcularValor();
		
		return pedidoRepository.save(pedido);
	}
	
	private void validarItens(Pedido pedido) {
	    pedido.getItens().forEach(item -> {
	        Produto produto = produtoRepository.findById(item.getProduto().getId()).get();
	        
	        item.setPedido(pedido);
	        item.setProduto(produto);
	        item.calcularPrecoTotal();
	    });
	}
	
	private void validarCliente(Pedido pedido) {
		Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).get();
		pedido.setCliente(cliente);
	}
}
