package com.mycompany.cupons;

import com.mycompany.ecommerce.Cupom;

public class CupomQuantidadeLimitada extends Cupom {

    private final int maximoUtilizacoes;
    private static int utilizacoesAtuais;

    public CupomQuantidadeLimitada(String cod, int desc, int max) {

        super(cod, desc);
        this.maximoUtilizacoes = max;
        this.utilizacoesAtuais = 0;
    }

    public int getMaximoUtilizacoes(){
        return maximoUtilizacoes;
    }
    
    public int getUtilizacoesAtuais(){
        return utilizacoesAtuais;
    }
    
    public void addUtilizacoesAtuais(){
        this.utilizacoesAtuais++;
    }
}
