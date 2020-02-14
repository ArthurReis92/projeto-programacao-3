package br.com.carnaval.excecoes;

public class BancoDeCadastroVazioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BancoDeCadastroVazioException(String msg) {
		super(msg);
	}
}
