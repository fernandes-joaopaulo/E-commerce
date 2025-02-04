package com.mycompany.ecommerce;

public class Produto {

    private final int id;
    private static int idAtual = 0;
    private final String nome;
    private double preco;

    public Produto(String nome, double p){
        this.id = idAtual;
        this.nome = nome;
        this.preco = p;
        idAtual++;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}

