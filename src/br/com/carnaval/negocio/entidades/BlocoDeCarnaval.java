package br.com.carnaval.negocio.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BlocoDeCarnaval {
	private String nome;
	private int id;
	private Date dataDeApresentacao;
	private String cidade;
	private Estilo estilo;
	public static SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public BlocoDeCarnaval(String nome, int id, Date dataDeApresentacao, String cidade, Estilo estilo) {
		this.nome = nome;
		this.id = id;
		this.dataDeApresentacao = dataDeApresentacao;
		this.cidade = cidade;
		this.estilo = estilo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataDeApresentacao() {
		return dataDeApresentacao;
	}

	public void setDataDeApresentacao(Date dataDeApresentacao) {
		this.dataDeApresentacao = dataDeApresentacao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\nCNPJ: " + id + "\nData de apresentação: " + formatoData.format(dataDeApresentacao)
				+ "\nCidade: " + cidade + "\nEstilo: " + estilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlocoDeCarnaval other = (BlocoDeCarnaval) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
