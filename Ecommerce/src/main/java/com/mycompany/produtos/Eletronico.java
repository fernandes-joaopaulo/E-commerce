package com.mycompany.produtos;

import com.mycompany.ecommerce.Produto;

public class Eletronico extends Produto {

    private int prazoGarantia;

    public Eletronico(int id, String nome, double p, int prazo) {
        super(id, nome, p);
        this.prazoGarantia = prazo;
    }

    public int getGarantia() {
        return prazoGarantia;
    }

    public void setGarantia(int prazo) {
        this.prazoGarantia = prazo;
    }
}
