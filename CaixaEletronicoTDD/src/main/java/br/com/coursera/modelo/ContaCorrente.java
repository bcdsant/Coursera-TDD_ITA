package br.com.coursera.modelo;

public class ContaCorrente{
	private int senha;
	private int numeroDaConta;
	private float saldoDaConta;
	
	public ContaCorrente(int numeroDaConta, int senha, float saldoDaConta) {
		this.numeroDaConta = numeroDaConta;
		this.senha = senha;
		this.saldoDaConta = saldoDaConta;
	}
	
	public ContaCorrente() {
		
	}

	public boolean senhaValida(int senha) {
		if(this.senha == senha) {
			return true;
		}
		return false;
	}


	public int getSenha() {
		return senha;
	}


	public void setSenha(int senha) {
		this.senha = senha;
	}


	public int getNumeroDaConta() {
		return numeroDaConta;
	}


	public void setNumeroDaConta(int numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}


	public float getSaldoDaConta() {
		return saldoDaConta;
	}


	public void setSaldoDaConta(float saldoDaConta) {
		this.saldoDaConta = saldoDaConta;
	}

}
