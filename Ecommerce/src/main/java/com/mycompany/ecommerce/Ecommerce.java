package com.mycompany.ecommerce;

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
            if (prod.equals(p)) {
                produtos.remove(p);
            } else {
                throw new ProdutoInvalidoException("Produto nao existe na loja");
            }
        }
    }

    public void adicionarVenda(Venda v) throws CupomInvalidoException, ProdutoInvalidoException {
        
        List<Produto> products = v.getProdutos();
        Cupom cupom = v.getCupom();

        if (!produtoValido(products)) {
            throw new ProdutoInvalidoException("Produto(s) nao existente(s) na loja!");
        }
        if (!cupomValido(cupom)) {
            throw new CupomInvalidoException("Código do cupom inválido!");
        }
        if (!cupom.getAtivo()) {
            throw new CupomInvalidoException("Cupom inativo!");
        }
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
