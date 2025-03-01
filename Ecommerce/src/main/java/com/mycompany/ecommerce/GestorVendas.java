package com.mycompany.ecommerce;

import java.util.*;
import com.mycompany.cupons.CupomQuantidadeLimitada;
import com.mycompany.cupons.CupomValorMinimo;
import com.mycompany.exceptions.CupomInvalidoException;

public class GestorVendas implements Relatorio {

    public GestorVendas() {
    }

    public void gerarRelatorioVendas(Ecommerce e) {

        List<Venda> vendas = e.getVendas();
        int totalVendas = vendas.size();
        double valorArrecadado = 0;
        int i = 1;
        for (Venda venda : vendas) {
            System.out.println("\nVENDA "+ i);
            venda.exibir();
            valorArrecadado += venda.calculaValorComDesconto();
            i++;
        }
        System.out.println("\nTotal de vendas realizadas: " + totalVendas);
        System.out.println("Valor arrecadado: " + valorArrecadado + " R$");
        System.out.println("\n");
    }

    public void listarCuponsAtivos(Ecommerce e) {

        List<Cupom> cupons = e.getCupons();
        if (!cupons.isEmpty()) {
            System.out.println("//CUPONS ATIVOS \n");
            for (Cupom cupom : cupons) {
                if (cupom.getAtivo()) {
                    System.out.println("Codigo: " + cupom.getCodigo());
                    System.out.println("Desconto: " + cupom.getDesconto() + "%");
                    if (cupom instanceof CupomValorMinimo) {
                        CupomValorMinimo cupomValorMinimo = (CupomValorMinimo) cupom;
                        System.out.println("Valor minimo: " + cupomValorMinimo.getValorMinimo() + " R$");
                    } else {
                        CupomQuantidadeLimitada cupomQtdLimitada = (CupomQuantidadeLimitada) cupom;
                        System.out.println("Maximo de utilizacoes: " + cupomQtdLimitada.getMaximoUtilizacoes());
                        System.out.println("Utilizacoes atuais: " + cupomQtdLimitada.getUtilizacoesAtuais());
                    }
                }
                System.out.println();
            }
        }
    }
}
