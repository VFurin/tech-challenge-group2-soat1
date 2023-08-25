package com.techchallenge.core.domain.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techchallenge.adapter.gateways.CategoriaGateway;
import com.techchallenge.adapter.gateways.ProdutoGateway;
import com.techchallenge.core.domain.entities.Produto;

@Service
public class ProdutoUseCase {

    private ProdutoGateway repository;

    private CategoriaGateway categoriaRepository;
    
    private static final String MSG_PRODUTO_EM_USO = "Produto em uso com o id %d";
    private static final String MSG_PRODUTO_NAO_EXISTE = "Não existe um cadastro de produto com código %d";
    private static final String MSG_CATEGORIA_NAO_EXISTE = "Não existe uma categoria com código %d";
    
    public Produto salvar(Produto produto) {
//    	Long categoriaId = produto.getCategoria().getId();
//    	
//        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
//        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CATEGORIA_NAO_EXISTE, categoriaId)));
//        
//        Categoria detached = new Categoria();
//        detached.setId(categoria.getId());
//        detached.setNome(categoria.getNome());
//        
//        produto.setCategoria(detached);
//        return repository.save(produto);
    	return null;
    }

    public Produto editar(Long produtoId) {
//        return repository.findById(produtoId)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        String.format(MSG_PRODUTO_NAO_EXISTE, produtoId)));
    	
    	return null;
    }

    public void excluir(Long produtoId) {
//    	try {
//    		repository.deleteById(produtoId);
//    		repository.flush();
//		} catch (DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
//		}
    }
    
    public List<Produto> buscarTodos() {
//    	return repository.findAll();
    	return null;
    }

    public List<Produto> buscarPorCategoria(String produtoCategoria) {
//      return repository.findByCategoriaNome(produtoCategoria);
    	return null;
    }
    
    public List<Produto> buscarPorCategoria(Long categoriaId) {
//    	return repository.findByCategoriaId(categoriaId);
    	return null;
    }
    
    public void atualizar(Long id, Produto produto) {
//        Produto produtoEntity = repository.findById(id)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NAO_EXISTE, id)));
//        
//    	Long categoriaId = produto.getCategoria().getId();
//    	
//        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
//        		.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CATEGORIA_NAO_EXISTE, categoriaId)));
//        
//        produto.setCategoria(categoria);
//        produtoEntity.setDescricao(produto.getDescricao());
//        produtoEntity.setImagem(produto.getImagem());
//        produtoEntity.setNome(produto.getNome());
//        produtoEntity.setPreco(produto.getPreco());
    }
}