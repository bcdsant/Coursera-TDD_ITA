package br.com.coursera.pilha.teste;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import br.com.coursera.pilha.Pilha;
import br.com.coursera.pilha.exception.PilhaCheiaException;
import br.com.coursera.pilha.exception.PilhaVaziaException;

public class TestePilha {

	private Pilha p;

	@Before
	public void inicializaPilha() {
		p = new Pilha(10);
	}

	@Test
	public void pilhaVazia() {
		assertTrue(p.estaVazia());
		assertEquals(0, p.tamanho());
	}

	@Test
	public void empilhaUmElemento() {
		p.empilha("primeiro");
		assertFalse(p.estaVazia());
		assertEquals(1,p.tamanho());
		assertEquals("primeiro",p.topo());
	}

	@Test 
	public void empilhaEDesempilha() {
		p.empilha("primeiro");
		p.empilha("segundo");
		assertEquals(2, p.tamanho());
		assertEquals("segundo", p.topo());
		Object elementoDesempilhado;
		elementoDesempilhado = p.desempilha();
		assertEquals(1,p.tamanho());
		assertEquals("segundo",elementoDesempilhado);
		assertEquals("primeiro",p.topo());
	}

	@Test(expected=PilhaVaziaException.class)
	public void removeDaPilhaVazia() {
		p.desempilha();		
	}

	@Test
	public void adicionaNaPilhaCheia() {
		for (int i = 0; i < 10; i++) {
			p.empilha("elemento"+i);
		}
		try {
			p.empilha("elemento do erro");
			fail();
		} catch (PilhaCheiaException e) {}
	}
}
