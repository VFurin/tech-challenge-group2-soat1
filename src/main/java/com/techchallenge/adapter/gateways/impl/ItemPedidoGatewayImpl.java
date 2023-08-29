package com.techchallenge.adapter.gateways.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techchallenge.adapter.gateways.ItemPedidoGateway;
import com.techchallenge.adapter.mapper.business.ItemPedidoBusinessMapper;
import com.techchallenge.adapter.mapper.business.PedidoBusinessMapper;
import com.techchallenge.adapter.mapper.business.ProdutoBusinessMapper;
import com.techchallenge.adapter.mapper.db.ItemPedidoEntityMapper;
import com.techchallenge.core.domain.entities.ItemPedido;
import com.techchallenge.core.domain.entities.Pedido;
import com.techchallenge.core.domain.entities.Produto;
import com.techchallenge.core.domain.entities.StatusPedido;
import com.techchallenge.core.domain.exception.EntidadeNaoEncontradaException;
import com.techchallenge.core.domain.exception.NegocioException;
import com.techchallenge.drivers.db.entities.ItemPedidoEntity;
import com.techchallenge.drivers.db.entities.PedidoEntity;
import com.techchallenge.drivers.db.entities.ProdutoEntity;
import com.techchallenge.drivers.db.repositories.ItemPedidoRepository;
import com.techchallenge.drivers.db.repositories.PedidoRepository;
import com.techchallenge.drivers.db.repositories.ProdutoRepository;

@Component
public class ItemPedidoGatewayImpl implements ItemPedidoGateway {

	private static final String MSG_ITEM_NAO_ENCONTRADO = "Item não encontrado no pedido com o id %d e produto com o id %d";
	private static final String MSG_ITEM_JA_ADICIONADO = "Item informado no pedido com o id %d e produto com o id %d já existente";
	private static final String MSG_PEDIDO_STATUS_NAO_ENCONTRADO = "Não existe pedido com o id %d e com o status %s";
	private static final String MSG_PRODUTO_NAO_EXISTE = "Não existe um cadastro de produto com código %d";

    @Autowired
    private ItemPedidoRepository repository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ItemPedidoEntityMapper mapper;
    
    @Autowired
    private ItemPedidoBusinessMapper businessMapper;
    
    @Autowired
    private PedidoBusinessMapper pedidoBusinessMapper;
    
    @Autowired
    private ProdutoBusinessMapper produtoBusinessMapper;
    
    @Transactional
    public ItemPedido adicionarItemAoPedido(Long pedidoId, ItemPedido itemPedido) {
    	Long produtoId = itemPedido.getProduto().getId();
    	
        PedidoEntity pedidoEntity = pedidoRepository.findByIdAndStatus(pedidoId, StatusPedido.RECEBIDO)
        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, pedidoId, StatusPedido.RECEBIDO.name())));
        
        ProdutoEntity produtoEntity = produtoRepository.findById(itemPedido.getProduto().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, itemPedido.getProduto().getId())));
        
        Pedido pedido = pedidoBusinessMapper.toModel(pedidoEntity);
    	Produto produto = produtoBusinessMapper.toModel(produtoEntity);
    	
    	List<ItemPedidoEntity> itens = repository.findByPedidoAndProduto(pedidoId, produtoId);
    	
    	if (!itens.isEmpty()) {
    		throw new NegocioException(String.format(MSG_ITEM_JA_ADICIONADO, pedidoId, produtoId));
    	}
    	
    	pedido.getItens().add(itemPedido);
    	
    	itemPedido.setPedido(pedido);
    	itemPedido.setProduto(produto);
    	itemPedido.calcularPrecoTotal();
    	
    	pedido.calcularValor();
    	
    	ItemPedidoEntity entity = mapper.toModel(itemPedido);
    	
    	pedidoEntity.setValor(pedido.getValor());
    	entity.setPedido(pedidoEntity);
    	entity.setProduto(produtoEntity);
    	
    	return businessMapper.toModel(repository.save(entity));
    }
    
    @Transactional
    public void atualizarItemAoPedido(Long pedidoId, ItemPedido itemPedido) {
    	Long produtoId = itemPedido.getProduto().getId();
    	List<ItemPedidoEntity> itens = repository.findByPedidoAndProduto(pedidoId, produtoId);
    	
    	ItemPedidoEntity item = itens.stream().findFirst()
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));
    	
        PedidoEntity pedidoEntity = pedidoRepository.findById(pedidoId)
        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, pedidoId, StatusPedido.RECEBIDO.name())));
        
        ProdutoEntity produtoEntity = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, itemPedido.getProduto().getId())));
    	
    	Produto produto = produtoBusinessMapper.toModel(produtoEntity);
    	Pedido pedido = pedidoBusinessMapper.toModel(pedidoEntity);
    	
    	// Faz o filter para pegar apenas o item a ser atualizado no pedido de origem.
    	ItemPedido itemAtualizar = pedido.getItens().stream()
    			.filter(f -> f.getId().equals(itemPedido.getId()))
    			.findFirst()
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));;
    	
    	// Atualiza valores com os quais foram consultados
    	itemAtualizar.setProduto(produto);
    	// Atualiza quantidade de itens
    	itemAtualizar.setQuantidade(itemPedido.getQuantidade());
    	// Calcula preço total
    	itemAtualizar.calcularPrecoTotal();
    	
    	// Atualiza estado do objeto na lista de itens
    	// do pedido de origem.
    	pedido.getItens().forEach(parametro -> {
    		if (parametro.getId().equals(itemAtualizar.getId())) {
    			parametro.setPrecoTotal(itemAtualizar.getPrecoTotal());
    			parametro.setQuantidade(itemAtualizar.getQuantidade());
    		}
    	});
    	
    	// Refaz o cáclulo do valor do pedido
    	pedido.calcularValor();
    	
    	// Atualiza a entidade do banco de dados com os valores atualizados.
    	item.setQuantidade(itemAtualizar.getQuantidade());
    	item.setPrecoTotal(itemAtualizar.getPrecoTotal());
    	item.getPedido().setValor(pedido.getValor());
    }
    
    @Transactional
    public void excluirItemAoPedido(Long pedidoId, Long produtoId) {
    	List<ItemPedidoEntity> itens = repository.findByPedidoAndProduto(pedidoId, produtoId);
    	
    	ItemPedidoEntity item = itens.stream().findFirst()
    			.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ITEM_NAO_ENCONTRADO, pedidoId, produtoId)));

    	repository.deleteById(item.getId());
    	repository.flush();
    	
        PedidoEntity pedidoEntity = pedidoRepository.findById(pedidoId)
        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PEDIDO_STATUS_NAO_ENCONTRADO, pedidoId, StatusPedido.RECEBIDO.name())));
        
        Pedido pedido = pedidoBusinessMapper.toModel(pedidoEntity);
        pedido.calcularValor();
        
        // Refaz o cáclulo do valor do pedido
        pedidoEntity.setValor(pedido.getValor());
    }
}