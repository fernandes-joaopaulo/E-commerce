package com.mycompany.produtos;

import com.mycompany.ecommerce.Produto;

public class Alimento extends Produto {

    private final String dataValidade;

    public Alimento(int id, String nome, double p, String data) {
        super(id, nome, p);
        this.dataValidade = data;
    }

    public String getValidade() {
        return dataValidade;
    }

}
