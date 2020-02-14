package br.com.carnaval.excecoes;

public class FilaVaziaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilaVaziaException(String msg) {
		super(msg);
	}
}
