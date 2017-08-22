package br.com.coursera.tdd.semana1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.coursera.tdd.semana1.exception.StringInvalidaException;

public class CamelCase {
	// Identifica o local entre qualquer...
	// ...caracter não maiúscula e maiúscula ("nomeComposto" => "nome[?]Composto").
	private static final String REGEX_CASO_1 = "(?<=[^A-Z])(?=[A-Z])";
	// ...letra maiúscula e outra maiúscula seguida de minúscula ("CPFContribuinte" => "CPF[?]Contribuinte").
	private static final String REGEX_CASO_2 = "(?<=[A-Z])(?=[A-Z][a-z])";
	// ...letra (maiúscula ou minúscula) e qualquer caracter que não seja letra ("primeiros10" => "primeiros[?]10").
	private static final String REGEX_CASO_3 = "(?<=[A-Za-z])(?=[^A-Za-z])";

	private static final String REGEX_DE_SEPARACAO = String.format("%s|%s|%s", REGEX_CASO_1, REGEX_CASO_2,
			REGEX_CASO_3);

	public static List<String> converterCamelCase(String original) throws StringInvalidaException {
		if (stringValida(original))
			return converterEmMinusculasQuandoNecessario(separarString(original));
		return null;
	}

	private static boolean stringValida(String stringTeste) throws StringInvalidaException {
		if (comecaComNumeros(stringTeste)) {
			throw new StringInvalidaException("A String não deve começar com números.");
		}
		if (contemCaracterEspecial(stringTeste)) {
			throw new StringInvalidaException("A String não deve conter caracteres especiais.");
		}
		return true;
	}

	private static boolean comecaComNumeros(String stringTeste) {
		return stringTeste.matches("^[0-9]+.+");
	}

	private static boolean contemCaracterEspecial(String stringTeste) {
		return stringTeste.matches("^([\\w])*([\\W])+([\\w]*)$");
	}
	
	private static List<String> separarString(String original) {
		return Arrays.asList(original.replaceAll(REGEX_DE_SEPARACAO, " ").split(" "));
	}

	//Não altera siglas ou acronimos (pelo menos duas letras maiúsculas).
	private static List<String> converterEmMinusculasQuandoNecessario(List<String> listaDePalavras) {
		List<String> listaConvertida = new ArrayList<String>();
		for (String palavra : listaDePalavras) {
			if (palavra.matches("[A-Z]{2,}"))
				listaConvertida.add(palavra);
			else
				listaConvertida.add(palavra.toLowerCase());
		}
		return listaConvertida;
	}

}
