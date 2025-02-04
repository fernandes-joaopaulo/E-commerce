package com.mycompany.ecommerce;

public class Cupom {

    private final String codigo;
    private final int percentualDesconto;
    private boolean ativo;

    public Cupom(String cod, int desc){
        this.codigo = cod;
        this.percentualDesconto = desc;
        this.ativo = true;
    }
    
    public String getCodigo() {
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
