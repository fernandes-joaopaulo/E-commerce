package com.mycompany.ecommerce;

import com.mycompany.cupons.CupomValorMinimo;
import com.mycompany.cupons.CupomQuantidadeLimitada;
import java.util.*;
import com.mycompany.exceptions.CupomInvalidoException;
import com.mycompany.exceptions.ProdutoInvalidoException;

public class Ecommerce {

    private List<Produto> produtos;
    private List<Venda> vendas;
    private List<Cupom> cupons;

    public Ecommerce() {
        produtos = new ArrayList<>();
        vendas = new ArrayList<>();
        cupons = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    
    public List<Venda> getVendas() {
        return vendas;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void cadastrarProduto(Produto p) {
        produtos.add(p);
    }

    public void removerProduto(Produto p) throws ProdutoInvalidoException {
        for (Produto prod : produtos) {
            if (prod.equals(p)){
                produtos.remove(p);
                return;
            }
        }
        throw new ProdutoInvalidoException("Produto nao existe na loja");
    }

    public void adicionarVenda(Venda v) throws CupomInvalidoException, ProdutoInvalidoException {
        
        List<Produto> products = v.getProdutos();
        Cupom cupom = v.getCupom();

        if (!produtoValido(products)) {
            throw new ProdutoInvalidoException("Produto(s) nao existente(s) na loja!");
        }
        if (!cupomValido(cupom)) {
            throw new CupomInvalidoException("Cupom invalido!");
        }
        if (!cupom.getAtivo()) {
            throw new CupomInvalidoException("Cupom inativo!");
        }
        if (cupom instanceof CupomQuantidadeLimitada){
            CupomQuantidadeLimitada cupomQtd = (CupomQuantidadeLimitada) cupom;
            if(cupomQtd.getUtilizacoesAtuais() == cupomQtd.getMaximoUtilizacoes())
                throw new CupomInvalidoException("Cupom " + cupomQtd.getCodigo() + " excedeu o maximo de utilizacoes");
            else
                cupomQtd.addUtilizacoesAtuais();
        }
        if (cupom instanceof CupomValorMinimo)
            if(((CupomValorMinimo) cupom).getValorMinimo() > v.calculaValorFinal())
                throw new CupomInvalidoException("Valor total insuficiente para uso do cupom " + cupom.getCodigo());
        
        vendas.add(v);
    }

    public boolean produtoValido(List<Produto> products) {

        int contador = 0;
        for (Produto i : products)
            for (Produto j : produtos)
                if (i == j)
                    contador++;
        if(contador == products.size()) return true; 
        else                            return false;
    }

    public boolean cupomValido(Cupom c) {

        for (Cupom cupom : cupons) {
            if (c.equals(cupom)) {
                return true;
            }
        }
        return false;
    }

    public void gerarCupom(Cupom c) throws CupomInvalidoException {

        if (cupomValido(c)) {
            throw new CupomInvalidoException("Cupom ja existente!");
        }
        cupons.add(c);
    }

    public void desativarCupom(Cupom c) {
        for (Cupom cupom : cupons) {
            if (cupom == c) {
                cupom.setEstado(false);
            }
        }
    }

}
