package br.com.coursera.modelo.teste.mock;

import br.com.coursera.modelo.Hardware;
import br.com.coursera.modelo.exceptions.HardwareDefeituosoException;

public class HardwareMock implements Hardware {
	private String numeroDaConta;
	private String numeroDasenha;
	private String valorDoSaque;
	private String valorDoDeposito;
	private boolean falharNoSaque = false;
	private boolean falharNoDeposito = false;
	private boolean falharNoValor = false;
	private boolean falharNoCartao = false;
	private boolean falharNaSenha = false;

	@Override
	public String pegarNumeroDaContaCartao() throws HardwareDefeituosoException{
		if(falharNoCartao)
			throw new HardwareDefeituosoException("Não foi possível identificar o cartão.");
		return numeroDaConta;
	}

	@Override
	public void entregarDinheiro() throws HardwareDefeituosoException {
		if (falharNoSaque)
			throw new HardwareDefeituosoException("Não foi possível realizar o saque.");
	}

	@Override
	public void lerEnvelope() {
		valorDoDeposito = pegarValor("deposito");
		if (falharNoDeposito)
			throw new HardwareDefeituosoException("Não foi possível ler o envelope.");
	}

	public String pegarNumeroDaSenhaDaConta() {
		if(falharNaSenha)
			throw new HardwareDefeituosoException("Não foi possível identificar a senha.");
		return numeroDasenha;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public String getNumeroDasenha() {
		return numeroDasenha;
	}

	public void setNumeroDasenha(String numeroDasenha) {
		this.numeroDasenha = numeroDasenha;
	}

	public String pegarValor(String operacao) throws HardwareDefeituosoException{
		if (falharNoValor)
			throw new HardwareDefeituosoException("Não foi possível ler o valor para a operação de " + operacao);
		if (operacao.equals("saque"))
			return valorDoSaque;
		if (operacao.equals("deposito"))
			return valorDoDeposito;
		return null;
	}

	public void setValorDoSaque(String valorDoSaque) {
		this.valorDoSaque = valorDoSaque;
	}

	public void setValorDoDeposito(String valorDoDeposito) {
		this.valorDoDeposito = valorDoDeposito;
	}

	public void falharNoSaque() {
		falharNoSaque = true;
	}

	public void falharNoDeposito() {
		falharNoDeposito = true;
	}

	public void falharNoValor() {
		falharNoValor = true;

	}

	public void falharNoNumeroDaContaCartao() {
		falharNoCartao = true;
	}

	public void falharNoNumeroDaSenha() {
		falharNaSenha = true;
		
	}

}
