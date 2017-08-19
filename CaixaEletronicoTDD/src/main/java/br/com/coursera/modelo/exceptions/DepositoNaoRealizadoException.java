package br.com.coursera.modelo.exceptions;

@SuppressWarnings("serial")
public class DepositoNaoRealizadoException extends RuntimeException {

	public DepositoNaoRealizadoException(String message) {
		super(message);
	}
}
