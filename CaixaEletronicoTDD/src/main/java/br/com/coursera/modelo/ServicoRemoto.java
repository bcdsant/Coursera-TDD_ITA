package br.com.coursera.modelo;

public interface ServicoRemoto {
	
	public ContaCorrente recuperarConta(int numeroDaConta);

	public void persistirConta(ContaCorrente conta);
		
}
