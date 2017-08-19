package br.com.coursera.modelo.teste.mock;

import br.com.coursera.modelo.ContaCorrente;
import br.com.coursera.modelo.ServicoRemoto;
import br.com.coursera.modelo.exceptions.ServicoRemotoException;

public class ServicoRemotoMock implements ServicoRemoto {
	
	private ContaCorrente contaCorrente;
	private boolean falharNaPersistencia = false;
	private boolean falharNaRecuperacaoDaConta = false;

	public void FalharNaPersistencia() {
		falharNaPersistencia = true;
	}

	public void FalharNaRecuperacaoDaConta() {
		falharNaRecuperacaoDaConta = true;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	@Override
	public ContaCorrente recuperarConta(int numeroDaConta) {
		if(falharNaRecuperacaoDaConta)
			throw new ServicoRemotoException("Não foi possível acessar essa conta.");
		return contaCorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	@Override
	public void persistirConta(ContaCorrente conta) {
		if (falharNaPersistencia)
			throw new ServicoRemotoException("Não foi possível salvar as alteraçoes dessa conta.");
		this.contaCorrente = conta;
	}

}
