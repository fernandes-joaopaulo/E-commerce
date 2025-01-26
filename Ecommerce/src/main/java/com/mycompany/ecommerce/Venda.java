package com.mycompany.ecommerce;

import com.mycompany.ecommerce.Cupom;
import com.mycompany.ecommerce.Produto;

public class Venda {

    private final int id;
    private final Produto[] produtos;
    private final Cupom cupom;

    public Venda(int id, Produto[] prod, Cupom c) {
        this.id = id;
        this.produtos = prod;
        this.cupom = c;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public Produto[] getProdutos() {
        return produtos;
    }
    
    public double calculaValorFinal(){
        double total = 0;
        for(Produto p : produtos){
            total += p.getPreco();
        }
        return total *= (cupom.getDesconto() / 100);
    }

    public void exibir() {
        System.out.println("Produtos: ");
        for(Produto p : produtos){
            System.out.println(p.getNome());
        }
        if(this.cupom != null)
            System.out.print("Cupom aplicado: "+this.cupom.getDesconto()+"%");
        System.out.println("Total: "+calculaValorFinal()+"R$");
    }
}
