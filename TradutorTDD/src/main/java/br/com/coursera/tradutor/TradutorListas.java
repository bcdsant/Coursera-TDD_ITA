package br.com.coursera.tradutor;

import java.util.List;
import java.util.ArrayList;

public class TradutorListas {

	private List<String> palavras = new ArrayList<String>();
	private List<String> traducoes = new ArrayList<String>();

	public boolean estaVazio() {		
		return palavras.isEmpty() && traducoes.isEmpty();
	}

	public void adicionaTraducao(String palavra, String traducao) {
		int index = palavras.indexOf(palavra);
		if(index!=-1) {
			traducoes.set(index, traducoes.get(index) + ", " + traducao);
		}else {
			palavras.add(palavra);
			traducoes.add(traducao);
		}
	}

	public String traduzir(String palavra) {
		int index = palavras.indexOf(palavra);
		if( index != -1) {
			return traducoes.get(index);
		}
		return null;
	}

	public String traduzirFrase(String frase) {
		String[] palavras = frase.split(" ");
		String fraseTraduzida = "";
		for (String palavra : palavras) {
			String traducao = primeiraTaducao(palavra);
			fraseTraduzida += " " + traducao;
		}
		return fraseTraduzida.trim();
	}

	private String primeiraTaducao(String palavra) {
		String traducao = traduzir(palavra);
		if(traducao.contains(",")) {
			traducao = traducao.substring(0,traducao.indexOf(','));
		}
		return traducao;
	}

}
