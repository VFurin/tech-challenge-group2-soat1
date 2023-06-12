package com.techchallenge.core.applications.service;

import com.techchallenge.core.applications.ports.CategoriaRepository;
import com.techchallenge.core.applications.ports.ProdutoRepository;
import com.techchallenge.core.domain.Categoria;
import com.techchallenge.core.domain.Produto;
import com.techchallenge.core.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        Categoria categoria = categoriaRepository.findByNome(produto.getCategoria().getNome());
        produto.setCategoria(categoria);
        return repository.save(produto);
    }

    public Produto editar(Long produtoId) {
        return repository.findById(produtoId)
                .orElseThrow(() -> new NegocioException(
                        String.format("Não existe um cadastro de produto com código %d", produtoId)));
    }

    public void excluir(Long produtoId) {
        repository.deleteById(produtoId);
    }
    public List<Produto> buscarTodos() {return repository.findAll();}

    public List<Produto> buscarPorCategoria(String produtoCategoria) {
        return repository.findByCategoriaNome(produtoCategoria);
    }
}
