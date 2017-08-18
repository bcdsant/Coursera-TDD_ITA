package br.com.coursera.tdd.sab;

@SuppressWarnings("serial")
public class UsuarioJaRegistradoException extends Exception {
	public UsuarioJaRegistradoException(String message)
    {
       super(message);
    }
}
