package br.com.coursera.modelo.exceptions;

@SuppressWarnings("serial")
public class UsuarioNaoLogadoException extends RuntimeException {
	public UsuarioNaoLogadoException(String mensagem) {
		super(mensagem);
	}
}
