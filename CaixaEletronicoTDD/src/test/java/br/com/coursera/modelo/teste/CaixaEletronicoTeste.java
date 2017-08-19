package br.com.coursera.modelo.teste;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import br.com.coursera.modelo.CaixaEletronico;
import br.com.coursera.modelo.ContaCorrente;
import br.com.coursera.modelo.exceptions.HardwareDefeituosoException;
import br.com.coursera.modelo.exceptions.ServicoRemotoException;
import br.com.coursera.modelo.teste.mock.HardwareMock;
import br.com.coursera.modelo.teste.mock.ServicoRemotoMock;

public class CaixaEletronicoTeste {

	private CaixaEletronico caixaEletronico;
	private HardwareMock hardwareMock;
	private ServicoRemotoMock servicoRemotoMock;
	private ContaCorrente contaCorrente;
	private static int numeroDaContaInt = 482786673;
	private static int senhaDaContaInt = 319128;
	private static float saldoDaConta = 100;
	private static String numeroDaContaString = "48278667-3";
	private static String senhaDaContaString = "319128";

	@Before
	public void inicializa() {
		hardwareMock = new HardwareMock();
		servicoRemotoMock = new ServicoRemotoMock();
		contaCorrente = new ContaCorrente(numeroDaContaInt, senhaDaContaInt, saldoDaConta);

		servicoRemotoMock.setContaCorrente(contaCorrente);

		hardwareMock.setNumeroDaConta(numeroDaContaString);
		hardwareMock.setNumeroDasenha(senhaDaContaString);
		hardwareMock.setValorDoSaque("50,00");
		hardwareMock.setValorDoDeposito("50,00");
		
	}

	@Test
	public void realizarLogin() {
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
	}

	@Test
	public void falhaNoLoginSenhaDigitadaIncorreta() {
		hardwareMock.setNumeroDasenha("165265");
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Não foi possível autenticar o usuário", caixaEletronico.logar());
	}

	@Test
	public void retornarSaldoDaConta() {
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}

	@Test
	public void realizouOSaque() {
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		assertEquals("Retire seu dinheiro", caixaEletronico.sacar());
		assertEquals("O saldo é R$50,00", caixaEletronico.saldo());
	}

	@Test
	public void realizarDeposito() {
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		assertEquals("Depósito recebido com sucesso", caixaEletronico.depositar());
		assertEquals("O saldo é R$150,00", caixaEletronico.saldo());
	}

	@Test
	public void falhaEmHardwareDuranteOSaque() {
		hardwareMock.falharNoSaque();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		try {
			caixaEletronico.sacar();
			fail();
		} catch (HardwareDefeituosoException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}
	
	@Test
	public void falhaEmHardwareDuranteODeposito() {
		hardwareMock.falharNoDeposito();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		try {
			caixaEletronico.depositar();
			fail();
		} catch (HardwareDefeituosoException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}
	
	@Test
	public void falhaEmHardwareNaLeituraDoValorDuranteODeposito() {
		hardwareMock.falharNoValor();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		try {
			caixaEletronico.depositar();
			fail();
		} catch (HardwareDefeituosoException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}
	
	@Test
	public void falhaEmHardwareNaLeituraDoValorDuranteOSaque() {
		hardwareMock.falharNoValor();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		try {
			caixaEletronico.sacar();
			fail();
		} catch (HardwareDefeituosoException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}
	
	@Test(expected=HardwareDefeituosoException.class)
	public void falhaEmHardwareAoPegarONumeroDaContaCartao() {
		hardwareMock.falharNoNumeroDaContaCartao();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		caixaEletronico.logar();
	}
	
	@Test(expected=HardwareDefeituosoException.class)
	public void falhaEmHardwareAoPegarONumeroDaSenha() {
		hardwareMock.falharNoNumeroDaSenha();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		caixaEletronico.logar();
	}
	
	@Test(expected=ServicoRemotoException.class)
	public void falhaEmServicoRemotoDuranteOSaque() {
		servicoRemotoMock.FalharNaPersistencia();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		caixaEletronico.sacar();
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}
	
	@Test(expected=ServicoRemotoException.class)
	public void falhaEmServicoRemotoDuranteODeposito() {
		servicoRemotoMock.FalharNaPersistencia();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		caixaEletronico.depositar();
		assertEquals("O saldo é R$100,00", caixaEletronico.saldo());
	}
	
	@Test(expected=ServicoRemotoException.class)
	public void falhaEmServicoRemotoAoLogar() {
		servicoRemotoMock.FalharNaRecuperacaoDaConta();
		caixaEletronico = new CaixaEletronico(hardwareMock, servicoRemotoMock);
		caixaEletronico.logar();
	}
	
}
