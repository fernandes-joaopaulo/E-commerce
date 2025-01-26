package com.mycompany.ecommerce;

public class Produto {

    private final int id;
    private final String nome;
    private double preco;

    public Produto(int id, String nome, double p){
        this.id = id;
        this.nome = nome;
        this.preco = p;
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

