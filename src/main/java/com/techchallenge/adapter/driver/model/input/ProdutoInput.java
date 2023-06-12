package com.techchallenge.adapter.driver.model.input;


import java.math.BigDecimal;

public class ProdutoInput {

    private String nome;

    private CategoriaInput categoria;

    private BigDecimal preco;

    private String descricao;

    private String imagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaInput getCategoria() {return categoria;}

    public void setCategoria(CategoriaInput categoria) {this.categoria = categoria;}

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
