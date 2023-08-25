package com.techchallenge.adapter.gateways.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.adapter.gateways.ItemPedidoGateway;
import com.techchallenge.core.domain.entities.ItemPedido;
import com.techchallenge.drivers.db.repositories.ItemPedidoRepository;
import com.techchallenge.drivers.db.repositories.PedidoRepository;
import com.techchallenge.drivers.db.repositories.ProdutoRepository;

@Component
public class ItemPedidoGatewayImpl implements ItemPedidoGateway {

	private static final String MSG_ITEM_NAO_ENCONTRADO = "Item não encontrado no pedido com o id %d e produto com o id %d";
	private static final String MSG_ITEM_JA_ADICIONADO = "Item informado no pedido com o id %d e produto com o id %d já existente";
	private static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido com o id %d não encontrado com o status RECEBIDO";
	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Produto com o id %d não encontrado";
	
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ItemPedido adicionarItemAoPedido(Long pedidoId, ItemPedido itemPedido) {
//    	Long produtoId = itemPedido.getProduto().getId();
//    	
//    	Pedido pedido = pedidoRepository.findByIdAndStatus(pedidoId, StatusPedido.RECEBIDO)
//    			.orElseThrow(() -> new NegocioException(String.format(MSG_PEDIDO_NAO_ENCONTRADO, pedidoId)));
//    	
//    	Produto produto = produtoRepository.findById(itemPedido.getProduto().getId())
//    			.orElseThrow(() -> new NegocioException(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId)));
//    	
//    	List<ItemPedido> itens = itemPedidoRepository.findByPedidoAndProduto(pedidoId, produtoId);
//    	
//    	if (!itens.isEmpty()) {
//    		throw new NegocioException(String.format(MSG_ITEM_JA_ADICIONADO, pedidoId, produtoId));
//    	}
//    	
//    	pedido.getItens().add(itemPedido);
//    	
//    	itemPedido.setPedido(pedido);
//    	itemPedido.setProduto(produto);
//    	itemPedido.calcularPrecoTotal();
//    	
//    	pedido.calcularValor();
//    	
//    	return itemPedidoRepository.save(itemPedido);
    	
    	return null;
    }
    
    @Transactional
    public void atualizarItemAoPedido(Long pedidoId, ItemPedido itemPedido) {
//    	Long produtoId = itemPedido.getProduto().getId();
//    	List<ItemPedido> itens = itemPedidoRepository.findByPedidoAndProduto(pedidoId, produtoId);
//    	
//    	ItemPedido item = itens.stream().findFirst()
//    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));
//    	
//    	item.setQuantidade(itemPedido.getQuantidade());
//    	item.calcularPrecoTotal();
//    	item.getPedido().calcularValor();
    }
    
    @Transactional
    public void excluirItemAoPedido(Long pedidoId, Long produtoId) {
//    	List<ItemPedido> itens = itemPedidoRepository.findByPedidoAndProduto(pedidoId, produtoId);
//    	
//    	ItemPedido item = itens.stream().findFirst()
//    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));
//
//    	itemPedidoRepository.deleteById(item.getId());
//    	
//    	Pedido pedido = pedidoRepository.findById(pedidoId).get();
//    	itemPedidoRepository.flush();
//    	
//    	pedido.calcularValor();
    	
    }
}