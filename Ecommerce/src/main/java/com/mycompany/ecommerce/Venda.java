package com.mycompany.ecommerce;

import com.mycompany.cupons.CupomQuantidadeLimitada;
import com.mycompany.cupons.CupomValorMinimo;
import com.mycompany.exceptions.CupomInvalidoException;
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
        return total;
    }
    
    public double calculaValorComDesconto(){
        if(this.cupom != null){
            double desconto = (this.cupom.getDesconto() / 100.0) * calculaValorFinal();
            double total = calculaValorFinal() - desconto;
            return total;
        }else{
            return calculaValorFinal();
        }
    }

    public void exibir() {
        System.out.println("Produtos: ");
        for(Produto p : produtos){
            System.out.println(p.getNome());
        }
        if(this.cupom != null)
            System.out.println("Cupom aplicado: "+this.cupom.getDesconto()+"%");
        System.out.println("Total: "+calculaValorComDesconto()+"R$");
    }
}
