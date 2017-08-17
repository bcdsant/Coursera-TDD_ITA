package br.com.coursera.tradutor;

import java.util.HashMap;
import java.util.Map;

public class TradutorMap {
	private Map<String,String> traducoes = new HashMap<>();

	public boolean estaVazio() {		
		return traducoes.isEmpty();
	}

	public void adicionaTraducao(String palavra, String traducao) {
		if(traducoes.containsKey(palavra)) {
			traducao = traduzir(palavra) + ", " + traducao;
		}
		traducoes.put(palavra,traducao);
	}

	public String traduzir(String palavra) {
		return traducoes.get(palavra);
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
