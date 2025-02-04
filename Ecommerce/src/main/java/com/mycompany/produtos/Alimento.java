package com.mycompany.produtos;

import com.mycompany.ecommerce.Produto;

public class Alimento extends Produto {

    private final String dataValidade;

    public Alimento(String nome, double p, String data) {
        super(nome, p);
        this.dataValidade = data;
    }

    public String getValidade() {
        return dataValidade;
    }

}
