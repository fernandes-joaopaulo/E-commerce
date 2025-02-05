package com.mycompany.ecommerce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import com.mycompany.cupons.CupomQuantidadeLimitada;
import com.mycompany.cupons.CupomValorMinimo;
import com.mycompany.exceptions.CupomInvalidoException;

public class EcommerceTest {
    
    public EcommerceTest(){}
        
    @Test
    public void vendaComCupomQtdLimitada(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("VALE10", 10, 3);        
        Venda venda = new Venda(produtos, cupom);
        double expected = 90.00;
        assertEquals(expected, venda.calculaValorComDesconto());
    }
    
    @Test
    public void vendaComCupomValorMinimo(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        CupomValorMinimo cupom = new CupomValorMinimo("VALE10", 10, 150.00);        
        Venda venda = new Venda(produtos, cupom);
        double expected = 90.00;
        assertEquals(expected, venda.calculaValorComDesconto());
    }
    
    @Test
    public void vendaSemCupom(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        Venda venda = new Venda(produtos, null);
        double expected = 100.00;
        assertEquals(expected, venda.calculaValorComDesconto());
    }
    
    @Test
    public void descontoCupomQtdLimitada(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("VALE10", 10, 3);        
        Venda venda = new Venda(produtos, cupom);
        double expected = 10.00;
        assertEquals(expected, venda.calculaValorFinal() - venda.calculaValorComDesconto());
    }
    
    @Test
    public void descontoCupomValorMinimo(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        CupomValorMinimo cupom = new CupomValorMinimo("VALE10", 10, 150.00);        
        Venda venda = new Venda(produtos, cupom);
        double expected = 10.00;
        assertEquals(expected, venda.calculaValorFinal() - venda.calculaValorComDesconto());
    }
    //TESTAR QUANTIDADES LIMITADAS DE CUPOM E VALORES MINIMOS
}
