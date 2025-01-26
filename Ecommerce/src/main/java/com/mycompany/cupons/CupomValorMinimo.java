package com.mycompany.cupons;

import com.mycompany.ecommerce.Cupom;

public class CupomValorMinimo extends Cupom {

    private final double valorMinimo;

    public CupomValorMinimo(int cod, int desc, double val) {

        super(cod, desc);
        this.valorMinimo = val;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

}
