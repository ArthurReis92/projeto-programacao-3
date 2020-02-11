package br.com.carnaval.excecoes;

public class CNPJNaoCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CNPJNaoCadastradoException(String msg) {
		super(msg);
	}
}
