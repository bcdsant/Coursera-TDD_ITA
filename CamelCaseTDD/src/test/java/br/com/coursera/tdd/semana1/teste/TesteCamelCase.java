package br.com.coursera.tdd.semana1.teste;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import br.com.coursera.tdd.semana1.CamelCase;
import br.com.coursera.tdd.semana1.exception.StringInvalidaException;

public class TesteCamelCase {

	private CamelCase camel;
	private List<String> listaTeste;
	
	@Before
	public void inicializa() {
		listaTeste = new ArrayList<String>();
		camel = new CamelCase();
	}
	
	@Test
	public void todasMinusculas() {
		String stringTeste = "todasminusculas";
		listaTeste.add(stringTeste);
		assertEquals(listaTeste, camel.converterCamelCase(stringTeste));
	}
	
	@Test
	public void primeiraMaiuscula() {
		String stringTeste = "Palavra";
		listaTeste.add(stringTeste.toLowerCase());
		assertEquals(listaTeste,camel.converterCamelCase(stringTeste));
	}
	
	@Test(expected=StringInvalidaException.class)
	public void stringVazia() {
		String stringTeste = "";
		listaTeste.add(stringTeste);
		assertEquals(listaTeste, camel.converterCamelCase(stringTeste));
	}
	
	@Test(expected=StringInvalidaException.class)
	public void stringNula() {
		String stringTeste = null;
		listaTeste.add(stringTeste);
		assertEquals(listaTeste, camel.converterCamelCase(stringTeste));
	}
	
	@Test
	public void nomeComposto() {
		String stringTeste = "nomeComposto";
		listaTeste.add("nome");
		listaTeste.add("composto");
		assertEquals(listaTeste,camel.converterCamelCase(stringTeste));
		listaTeste.add("triplo");
		stringTeste="NomeCompostoTriplo";
		assertEquals(listaTeste,camel.converterCamelCase(stringTeste));
	}
	
	@Test
	public void sigla() {
		String stringTeste = "CPF";
		listaTeste.add("CPF");
		assertEquals(listaTeste,camel.converterCamelCase(stringTeste));
	}
	
	@Test
	public void siglaNaPalavra() {
		String stringTeste = "numeroCPF";
		listaTeste.add("numero");
		listaTeste.add("CPF");
		assertEquals(listaTeste,camel.converterCamelCase(stringTeste));
		listaTeste.add("contribuinte");
		stringTeste = "numeroCPFContribuinte";
		assertEquals(listaTeste,camel.converterCamelCase(stringTeste));
	}
	
	@Test(expected=StringInvalidaException.class)
	public void digitosNoInicio() {
		String stringTeste = "10Primeiros";
		camel.converterCamelCase(stringTeste);
	}
	
	@Test(expected=StringInvalidaException.class)
	public void caracterEspecial() {
		String stringTeste = "nome#Composto";
		camel.converterCamelCase(stringTeste);
	}
	
	@Test
	public void digitosNoMeio() {
		String stringTeste = "recupera10Primeiros";
		listaTeste.add("recupera");
		listaTeste.add("10");
		listaTeste.add("primeiros");
		assertEquals(listaTeste, camel.converterCamelCase(stringTeste));		
	}
	
}
