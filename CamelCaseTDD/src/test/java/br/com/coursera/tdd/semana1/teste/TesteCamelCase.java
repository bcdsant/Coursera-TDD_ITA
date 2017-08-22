package br.com.coursera.tdd.semana1.teste;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import br.com.coursera.tdd.semana1.CamelCase;
import br.com.coursera.tdd.semana1.exception.StringInvalidaException;

public class TesteCamelCase {

	private List<String> resultadoEsperado = null;

	@Before
	public void inicializa() {
		resultadoEsperado = new ArrayList<String>();
	}

	@Test
	public void todasMinusculas() {
		resultadoEsperado.add("nome");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("nome"));
	}

	@Test
	public void primeiraMaiuscula() {
		resultadoEsperado.add("nome");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("Nome"));
	}

	@Test
	public void nomeComposto() {
		resultadoEsperado.add("nome");
		resultadoEsperado.add("composto");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("nomeComposto"));
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("NomeComposto"));
		resultadoEsperado.add("triplo");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("nomeCompostoTriplo"));
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("NomeCompostoTriplo"));
	}

	@Test
	public void siglasEAcronimos() {
		resultadoEsperado.add("CPF");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("CPF"));
	}

	@Test
	public void siglasEAcronimosEmUmaFrase() {
		resultadoEsperado.add("numero");
		resultadoEsperado.add("CPF");
		resultadoEsperado.add("contribuinte");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("numeroCPFContribuinte"));
	}

	@Test
	public void digitosEntrePalavras() {
		resultadoEsperado.add("recupera");
		resultadoEsperado.add("10");
		resultadoEsperado.add("primeiros");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("recupera10Primeiros"));
	}

	@Test
	public void digitosESiglasOuAcronimosEmUmaFrase() {
		resultadoEsperado.add("recupera");
		resultadoEsperado.add("10");
		resultadoEsperado.add("primeiros");
		resultadoEsperado.add("CPF");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("recupera10PrimeirosCPF"));
	}

	@Test(expected = StringInvalidaException.class)
	public void stringInvalidaPorComecarComDigitos() {
		resultadoEsperado.add("10");
		resultadoEsperado.add("primeiros");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("10Primeiros"));
	}

	@Test(expected = StringInvalidaException.class)
	public void stringInvalidaCaracteresEspeciais() {
		resultadoEsperado.add("nome");
		resultadoEsperado.add("#");
		resultadoEsperado.add("composto");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("nome#Composto"));
	}

	@Test(expected = StringInvalidaException.class)
	public void stringInvalidaSomenteCaracteresEspeciais() {
		resultadoEsperado.add("#@%$¨&*");
		assertEquals(resultadoEsperado, CamelCase.converterCamelCase("#@%$¨&*"));
	}
}
