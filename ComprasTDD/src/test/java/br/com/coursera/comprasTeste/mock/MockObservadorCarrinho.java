package br.com.coursera.comprasTeste.mock;

import static org.junit.Assert.*;

import br.com.coursera.compras.ObservadorCarrinho;

public class MockObservadorCarrinho implements ObservadorCarrinho {

	private String nomeRecebido;
	private int valorRecebido;
	private boolean excessao = false;

	@Override
	public void produtoAdicionado(String nome, int valor) {
		if (excessao)
			throw new RuntimeException("Problema simulado pelo mock");
		nomeRecebido = nome;
		valorRecebido = valor;

	}

	public void verificaRecebimentoProduto(String nomeEsperado, int valorEsperado) {
		assertEquals(nomeEsperado, nomeRecebido);
		assertEquals(valorEsperado, valorRecebido);
	}

	public void gereExcessao() {
		excessao = true;
	}

}
