package com.mycompany.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import com.mycompany.cupons.CupomQuantidadeLimitada;
import com.mycompany.cupons.CupomValorMinimo;
import com.mycompany.exceptions.CupomInvalidoException;
import com.mycompany.exceptions.ProdutoInvalidoException;

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
    public void cupomComLimiteExcedido(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        CupomQuantidadeLimitada cupom = new CupomQuantidadeLimitada("VALE10", 10, 1);        
        Venda venda = new Venda(produtos, cupom);
        Venda venda2 = new Venda(produtos, cupom);
        Ecommerce e = new Ecommerce();
        e.cadastrarProduto(produto);
        
        CupomInvalidoException exception = assertThrows(CupomInvalidoException.class, () -> {
            e.gerarCupom(cupom);
            e.adicionarVenda(venda);
            e.adicionarVenda(venda2);
        });
        assertEquals("Cupom " + cupom.getCodigo() + " excedeu o maximo de utilizacoes", exception.getMessage());
    }
    
    @Test
    public void cupomAplicadoSemValorMinimo(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        CupomValorMinimo cupom = new CupomValorMinimo("VALE10", 10, 200.00);        
        Venda venda = new Venda(produtos, cupom);
        Ecommerce e = new Ecommerce();
        e.cadastrarProduto(produto);
        
        CupomInvalidoException exception = assertThrows(CupomInvalidoException.class, () -> {
            e.gerarCupom(cupom);
            e.adicionarVenda(venda);
        });
        assertEquals("Valor total insuficiente para uso do cupom " + cupom.getCodigo(), exception.getMessage());
    }
    
    @Test
    public void cuponsValidos(){
        CupomValorMinimo cupom = new CupomValorMinimo("VALE10", 10, 200.00);        
        Ecommerce e = new Ecommerce();
        e.getCupons().add(cupom);
        
        assertTrue(e.cupomValido(cupom));
    }
    
    @Test
    public void cuponsInvalidos(){
        CupomValorMinimo cupom = new CupomValorMinimo("VALE10", 10, 200.00);        
        Ecommerce e = new Ecommerce();

        assertFalse(e.cupomValido(cupom));      
    }
    
    @Test
    public void cuponsAtivos(){
        Cupom cupom = new Cupom("VALE10", 10);        
        assertTrue(cupom.getAtivo());      
    }
     
    @Test
    public void cuponsInativos(){
        Cupom cupom = new Cupom("VALE10", 10);   
        cupom.setEstado(false);
        assertFalse(cupom.getAtivo());      
    }
    
    @Test
    public void vendaComProdutoInexistente(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        Venda venda = new Venda(produtos, null);
        Ecommerce e = new Ecommerce();
        
        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class, () -> {
            e.adicionarVenda(venda);
        });
        assertEquals("Produto(s) nao existente(s) na loja!", exception.getMessage());
    }
    
    @Test
    public void remocaoProdutoExistente(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        Ecommerce e = new Ecommerce();
        e.cadastrarProduto(produto);
        e.getProdutos().remove(produto);
        
        assertFalse(e.produtoValido(produtos));
    }
    
    @Test
    public void remocaoProdutoInexistente(){
        Produto produto = new Produto("Produto", 100.00);
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        Ecommerce e = new Ecommerce();
        
        ProdutoInvalidoException exception = assertThrows(ProdutoInvalidoException.class, () -> {
            e.removerProduto(produto);
        });
        assertEquals("Produto nao existe na loja", exception.getMessage());
    }
}
