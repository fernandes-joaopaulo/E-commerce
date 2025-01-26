package com.mycompany.produtos;

import com.mycompany.ecommerce.Produto;

public class Roupa extends Produto {

    private String tamanho;
    private String cor;

    public Roupa(int id, String nome, double p, String tam, String cor) {
        super(id, nome, p);
        this.tamanho = tam;
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setTamanho(String tam) {
        this.tamanho = tam;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
