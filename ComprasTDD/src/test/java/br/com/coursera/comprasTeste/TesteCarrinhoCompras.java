package br.com.coursera.comprasTeste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.coursera.compras.CarrinhoCompras;
import br.com.coursera.compras.Produto;
import br.com.coursera.comprasTeste.mock.MockObservadorCarrinho;

public class TesteCarrinhoCompras {
	
	private CarrinhoCompras c;
	private MockObservadorCarrinho mock1, mock2, mock3;
	
	@Before
	public void inicializa() {
		c = new CarrinhoCompras();
		mock1 = new MockObservadorCarrinho();
		mock2 = new MockObservadorCarrinho();
		mock3 = new MockObservadorCarrinho();
	}
	
	@Test
	public void totalCarrinho() {
		c.adicionaProduto(new Produto("tenis", 100));
		c.adicionaProduto(new Produto("camiseta", 50));
		c.adicionaProduto(new Produto("bermuda", 70));
		assertEquals(220, c.total());
	}
	
	@Test
	public void escutaAdicaoDeProduto() {
		c.adicionarObservador(mock1);
		c.adicionaProduto(new Produto("tenis", 100));
		mock1.verificaRecebimentoProduto("tenis", 100);
	}
	
	@Test
	public void adicionarDoisObservadores() {
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionaProduto(new Produto("tenis", 100));
		mock1.verificaRecebimentoProduto("tenis", 100);
		mock2.verificaRecebimentoProduto("tenis", 100);
	}
	
	@Test
	public void continuaNotificandoComErroEmObservador() {
		mock2.gereExcessao();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionarObservador(mock3);
		c.adicionaProduto(new Produto("tenis", 100));
		mock1.verificaRecebimentoProduto("tenis", 100);
		mock3.verificaRecebimentoProduto("tenis", 100);
	}

}
