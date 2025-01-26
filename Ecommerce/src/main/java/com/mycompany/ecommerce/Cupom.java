package com.mycompany.ecommerce;

public class Cupom {

    private final int codigo;
    private final int percentualDesconto;
    private boolean ativo;

    public Cupom(int cod, int desc){
        this.codigo = cod;
        this.percentualDesconto = desc;
        this.ativo = true;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public int getDesconto() {
        return percentualDesconto;
    }

    public boolean getAtivo() {
        return ativo;
    }
    
    public void setEstado(boolean estado){
        this.ativo = estado;
    }
}
