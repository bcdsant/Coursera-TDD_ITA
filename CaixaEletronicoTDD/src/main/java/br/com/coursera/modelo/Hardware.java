package br.com.coursera.modelo;

public interface Hardware {
	
	public String pegarNumeroDaContaCartao();

	public void entregarDinheiro();

	public void lerEnvelope();

	public String pegarNumeroDaSenhaDaConta();
	
	public String pegarValor(String operacao);
	
}
