package br.com.coursera.tdd.semana1.exception;

public class StringInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StringInvalidaException(String message) {
		super(message);
	}
	
}
