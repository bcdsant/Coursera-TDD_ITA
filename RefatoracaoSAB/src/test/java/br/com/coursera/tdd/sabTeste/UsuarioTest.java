package br.com.coursera.tdd.sabTeste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.coursera.tdd.sab.Livro;
import br.com.coursera.tdd.sab.Usuario;

public class UsuarioTest {

	@Before
	public void SetUp() {
		usuario = new Usuario("Jose");
	}

	@Test
	public void whenSituacaoInicialEntaoLivroDisponivelParaEmprestimo() {
		// T1: Testa condições de início!
		// Assumo que Biblioteca não deixa criar usuario com
		// com nomes vazios ou nulos!
		assertEquals(0, (usuario.getLivros()).size());
		assertEquals("Jose", usuario.getNome());
	}

	@Test
	public void whenAnexaLivroNaoNuloAListaDeLivrosEmprestadosPeloUsuarioEntaoIncrementaHumDeListaDeLivrosEmprestados() {
		// T2: Anexa livro não nulo
		Livro livro = new Livro("Java Design Patterns", "Pankaj Kumar");
		int tam = (usuario.getLivros()).size();
		usuario.anexaLivroAoUsuario(livro);
		assertEquals(tam + 1, (usuario.getLivros()).size());
	}

	@Test
	public void whenAnexaLivroNuloAListaDeLivrosEmprestadosPeloUsuarioEntaoTamanhoDeListaDeLivrosEmprestadosNaoMuda() {
		// T3: Anexa livro nulo
		Livro livro = new Livro("Java Design Patterns", "Pankaj Kumar");
		usuario.anexaLivroAoUsuario(livro);
		Livro livro1 = new Livro("Clojure", "Sally Fields");
		usuario.anexaLivroAoUsuario(livro1);
		Livro livro2 = null;
		int tam = (usuario.getLivros()).size();
		usuario.anexaLivroAoUsuario(livro2);
		assertEquals(tam, (usuario.getLivros()).size());
	}

	@Test
	public void whenDesanexaLivroNaoNuloDeLivrosEmprestadosPeloUsuarioDecrementaHumDeListaDeLivrosEmprestados() {
		// T4: Desanexa livro não nulo
		Livro livro = new Livro("Java Design Patterns", "Pankaj Kumar");
		usuario.anexaLivroAoUsuario(livro);
		Livro livro1 = new Livro("Clojure", "Sally Fields");
		usuario.anexaLivroAoUsuario(livro1);
		int tam = (usuario.getLivros()).size();
		usuario.desanexaLivroDoUsuario(livro);
		assertEquals(tam - 1, (usuario.getLivros()).size());
	}

	@Test
	public void whenDesanexaLivroNuloDeLivrosEmprestadosPeloUsuarioEntaoTamanhoDeListaDeLivrosEmprestadosNaoMuda() {
		// T5: Desanexa livro nulo
		Livro livro = new Livro("Java Design Patterns", "Pankaj Kumar");
		usuario.anexaLivroAoUsuario(livro);
		Livro livro1 = new Livro("Clojure", "Sally Fields");
		usuario.anexaLivroAoUsuario(livro1);
		Livro livro2 = null;
		usuario.anexaLivroAoUsuario(livro2);
		int tam = (usuario.getLivros()).size();
		usuario.desanexaLivroDoUsuario(livro2);
		assertEquals(tam, (usuario.getLivros()).size());
	}

	private Usuario usuario;
}
