package br.com.coursera.modelo;

import java.text.DecimalFormat;

import br.com.coursera.modelo.exceptions.HardwareDefeituosoException;
import br.com.coursera.modelo.exceptions.ServicoRemotoException;
import br.com.coursera.modelo.exceptions.UsuarioNaoLogadoException;

public class CaixaEletronico {
	private Hardware hardware;
	private ServicoRemoto servicoRemoto;
	private ContaCorrente contaCorrente;
	private boolean usuarioLogado = false;

	public CaixaEletronico(Hardware hardware, ServicoRemoto servicoRemoto) {
		this.hardware = hardware;
		this.servicoRemoto = servicoRemoto;
	}

	public String logar() {
		contaCorrente = servicoRemoto.recuperarConta(getNumeroDaConta());
		if (contaCorrente.senhaValida(getSenhaDaConta())) {
			usuarioLogado = true;
			return "Usuário Autenticado";
		}
		return "Não foi possível autenticar o usuário";
	}

	public String sacar() throws UsuarioNaoLogadoException, HardwareDefeituosoException, ServicoRemotoException {
		usuarioEstaLogado();
		return realizarSaque();
	}

	private String realizarSaque() throws UsuarioNaoLogadoException, HardwareDefeituosoException, ServicoRemotoException {
		if (getValorDoSaque() <= contaCorrente.getSaldoDaConta()) {
			hardware.entregarDinheiro();
			contaCorrente.setSaldoDaConta(contaCorrente.getSaldoDaConta() - getValorDoSaque());
			servicoRemoto.persistirConta(contaCorrente);
			return "Retire seu dinheiro";
		}
		return "Saldo insuficiente";
	}

	public String depositar() throws UsuarioNaoLogadoException, HardwareDefeituosoException, ServicoRemotoException {
		usuarioEstaLogado();
		realizarDeposito();
		return "Depósito recebido com sucesso";
	}

	private void realizarDeposito() throws HardwareDefeituosoException, ServicoRemotoException {
		hardware.lerEnvelope();
		contaCorrente.setSaldoDaConta(contaCorrente.getSaldoDaConta() + getValorDoDeposito());
		servicoRemoto.persistirConta(contaCorrente);
	}

	public String saldo() throws UsuarioNaoLogadoException {
		usuarioEstaLogado();
		DecimalFormat formatter = new DecimalFormat("#.00");
		String saldoFormatado = formatter.format(contaCorrente.getSaldoDaConta());
		return ("O saldo é R$" + saldoFormatado);
	}

	private int getSenhaDaConta() {
		return Integer.parseInt(hardware.pegarNumeroDaSenhaDaConta().replaceAll("([A-Za-z])*([\\W])*", ""));
	}

	private int getNumeroDaConta() {
		return Integer.parseInt(hardware.pegarNumeroDaContaCartao().replaceAll("([A-Za-z])*([\\W])*", ""));
	}

	private float getValorDoSaque() {
		return Float.parseFloat(hardware.pegarValor("saque").replaceAll(",", "."));
	}

	private float getValorDoDeposito() {
		return Float.parseFloat(hardware.pegarValor("deposito").replaceAll(",", "."));
	}

	private boolean usuarioEstaLogado() throws UsuarioNaoLogadoException {
		if (usuarioLogado)
			return true;
		throw new UsuarioNaoLogadoException("Não é possível realizar essa operação. Você não está logado");
	}

}
