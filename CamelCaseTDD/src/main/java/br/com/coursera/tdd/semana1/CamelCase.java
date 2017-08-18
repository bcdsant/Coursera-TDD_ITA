package br.com.coursera.tdd.semana1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.com.coursera.tdd.semana1.exception.StringInvalidaException;

public class CamelCase {

	private List<String> lista = new ArrayList<String>();

	public List<String> converterCamelCase(String stringTeste) throws StringInvalidaException{
		if (isValidString(stringTeste)) {
			lista = separarString(stringTeste);
			lista = convertEmMinusculas(lista);
		}
		return lista;
	}

	private boolean isValidString(String stringTeste) throws StringInvalidaException {
		if (stringTeste == null || stringTeste.isEmpty()) { // Verifica se a String é nula ou está vazia
			throw new StringInvalidaException("A String está vazia.");
		}
		if (stringTeste.matches("^[0-9]+.+")) { // Verifica se a String começa com dígitos
			throw new StringInvalidaException("A String não deve começar com números.");
		}
		if (stringTeste.matches("^([\\w])*(\\W)(([\\w])*)$")) {// Verifica se a String contém caracteres especiais
			throw new StringInvalidaException("A String não deve conter caracteres especiais.");
		}
		return true;
	}

	// Converte palavras em minúsculas, ignorando as siglas (EX.: CPF, RG, HTML)
	// São considerados siglas todas as palavras que apresentarem todas as letras
	// maiúsculas
	private List<String> convertEmMinusculas(List<String> palavras) {
		List<String> temp = new ArrayList<String>();
		for (String string : palavras) {
			if (string.matches("^([A-Z])([a-z])+")) { // Verificando se somente a primeira é maiúscula
				temp.add(string.toLowerCase());
			} else {
				temp.add(string);
			}
		}
		return temp;
	}

	private List<String> separarString(String tempString) {
		String[] temp;
		tempString = tempString.replaceAll( // Adiciona um marcador (" ") entre...
				String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", // ... uma letra maiúscula e outra maiúscula
																		// seguida de minúscula ("CPFContribuinte" =>
																		// "CPF Contribuinte").
						"(?<=[^A-Z])(?=[A-Z])", // ... qualquer caracter não maiúscula e maiúscula ("nomeComposto" =>
												// "nome Composto", "10Primeiros" => "10 Primeiros", "NomeComposto" =>
												// "Nome Composto").
						"(?<=[A-Za-z])(?=[^A-Za-z])" // ... qualquer letra (maiúscula ou minúscula) e qualquer não letra
														// ("primeiros10" => "primeiros 10", "numero?" => "numero ?").
				), " ");
		temp = tempString.split(" ");
		return Arrays.asList(temp);// Convertendo o Array em ArrayList e retornando
	}
}
