package com.techchallenge.adapter.driver.model;

import com.techchallenge.core.domain.Categoria;

import java.math.BigDecimal;

public class ProdutoModel {

    private String nome;
    private CategoriaResumoModel categoria;

    private BigDecimal preco;

    private String descricao;

    private String imagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaResumoModel getCategoria() {return categoria;}

    public void setCategoria(CategoriaResumoModel categoria) {this.categoria = categoria;}

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
