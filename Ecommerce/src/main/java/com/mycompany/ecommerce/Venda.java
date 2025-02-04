package com.mycompany.ecommerce;

import com.mycompany.ecommerce.Cupom;
import com.mycompany.ecommerce.Produto;
import java.util.*;

public class Venda {

    private final int id;
    private static int idAtual;
    private List<Produto> produtos = new ArrayList<>();
    private final Cupom cupom;

    public Venda(List<Produto> prod, Cupom c) {
        this.id = idAtual;
        this.produtos = prod;
        this.cupom = c;
        idAtual++;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public double calculaValorFinal(){
        double total = 0;
        for(Produto p : produtos){
            total += p.getPreco();
        }
        double desconto = total * (cupom.getDesconto() / 100.0);
        return total - desconto;
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
