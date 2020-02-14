package br.com.carnaval.excecoes;

public class NaoCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoCadastradoException(String msg) {
		super(msg);
	}
}
