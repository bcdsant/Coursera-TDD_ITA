package br.com.coursera.pilha;

import br.com.coursera.pilha.exception.PilhaCheiaException;
import br.com.coursera.pilha.exception.PilhaVaziaException;

public class Pilha {

	private Object[] elementos;
	private int quantidade = 0;


	public Pilha(int maximo) {
		this.elementos  = new Object[maximo];
	}

	public boolean estaVazia() {
		return (quantidade == 0);
	}

	public int tamanho() {
		return quantidade;
	}

	public void empilha(Object elemento) {
		if(quantidade == elementos.length) {
			throw new PilhaCheiaException("Pilha Cheia. Não é possível empilhar");
		}
		elementos[quantidade] = elemento;
		quantidade ++;
	}

	public Object topo() {
		return elementos[quantidade-1] ;
	}

	public Object desempilha() {
		if(this.estaVazia()) {
			throw new PilhaVaziaException("Pilha Vazia. Não é possível desempilhar.");
		}
		Object topo = this.topo();
		quantidade --;
		return topo;
	}

}
