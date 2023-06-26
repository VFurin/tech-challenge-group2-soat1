package com.techchallenge.core.applications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techchallenge.core.applications.ports.ItemPedidoRepository;
import com.techchallenge.core.applications.ports.PedidoRepository;
import com.techchallenge.core.applications.ports.ProdutoRepository;
import com.techchallenge.core.domain.ItemPedido;
import com.techchallenge.core.domain.Pedido;
import com.techchallenge.core.domain.Produto;

@Service
public class ItemPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemPedido adicionarItemAoPedido(Long idPedido, ItemPedido itemPedido) {
    	Pedido pedido = pedidoRepository.findById(idPedido).get();
    	Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).get();
    	
    	pedido.getItens().add(itemPedido);
    	
    	itemPedido.setPedido(pedido);
    	itemPedido.setProduto(produto);
    	itemPedido.calcularPrecoTotal();
    	
    	return itemPedidoRepository.save(itemPedido);
    }
}