package com.mycompany.ecommerce;

import com.mycompany.produtos.Alimento;
import com.mycompany.produtos.Eletronico;
import com.mycompany.produtos.Roupa;
import com.mycompany.exceptions.CupomInvalidoException;
import com.mycompany.exceptions.ProdutoInvalidoException;
import com.mycompany.cupons.CupomQuantidadeLimitada;
import com.mycompany.cupons.CupomValorMinimo;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Ecommerce loja = new Ecommerce();

        // Criando produtos
        Alimento whey = new Alimento("Whey Protein", 99.00, "31/12/2025");
        Roupa camisa = new Roupa("Camisa Oversized", 120.00, "G", "Preta");
        Eletronico pc = new Eletronico("Computador", 3000.00, 12);

        Alimento arroz = new Alimento("Arroz Integral", 25.00, "30/06/2024");
        Roupa jaqueta = new Roupa("Jaqueta de Couro", 350.00, "M", "Marrom");
        Eletronico celular = new Eletronico("Celular Android", 1500.00, 24);

        Alimento macarrao = new Alimento("Macarrão Integral", 8.50, "15/03/2024");
        Roupa tenis = new Roupa("Tênis Esportivo", 250.00, "42", "Branco");
        Eletronico tablet = new Eletronico("Tablet", 800.00, 18);

        // Cadastrando produtos na loja
        loja.cadastrarProduto(whey);
        loja.cadastrarProduto(camisa);
        loja.cadastrarProduto(pc);
        loja.cadastrarProduto(arroz);
        loja.cadastrarProduto(jaqueta);
        loja.cadastrarProduto(celular);
        loja.cadastrarProduto(macarrao);
        loja.cadastrarProduto(tenis);
        loja.cadastrarProduto(tablet);

        // Criando cupons de desconto
        CupomQuantidadeLimitada cupom1 = new CupomQuantidadeLimitada("VALE10", 10, 2);
        CupomQuantidadeLimitada cupom2 = new CupomQuantidadeLimitada("VALE20", 20, 2);
        CupomQuantidadeLimitada cupom3 = new CupomQuantidadeLimitada("VALE50", 50, 2);
        CupomValorMinimo cupom4 = new CupomValorMinimo("VALE40", 40, 3000.00);

        //Adicionando cupons a loja
        try{
            loja.gerarCupom(cupom1);
            loja.gerarCupom(cupom2);
            loja.gerarCupom(cupom3);
            loja.gerarCupom(cupom4);
        }catch(CupomInvalidoException e){
            System.out.println("Erro: " + e.getMessage());
        }
                
        // Criando listas de produtos para vendas
        List<Produto> produtosVenda1 = new ArrayList<>();
        produtosVenda1.add(whey);
        produtosVenda1.add(arroz);
        produtosVenda1.add(macarrao);

        List<Produto> produtosVenda2 = new ArrayList<>();
        produtosVenda2.add(camisa);
        produtosVenda2.add(jaqueta);

        List<Produto> produtosVenda3 = new ArrayList<>();
        produtosVenda3.add(pc);
        produtosVenda3.add(celular);
        produtosVenda3.add(tablet);
        produtosVenda3.add(tenis);

        // Criando vendas
        Venda venda1 = new Venda(produtosVenda1, cupom1);
        Venda venda2 = new Venda(produtosVenda2, cupom1);
        Venda venda3 = new Venda(produtosVenda3, cupom2);

        // Adicionando vendas na loja
        try {
            loja.adicionarVenda(venda1);
            loja.adicionarVenda(venda2);
            loja.adicionarVenda(venda3);

            // Gerando relatório de vendas
            GestorVendas gestor = new GestorVendas();
            gestor.gerarRelatorioVendas(loja);
            gestor.listarCuponsAtivos(loja);
        } catch (ProdutoInvalidoException | CupomInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
