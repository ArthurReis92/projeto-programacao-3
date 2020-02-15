package br.com.carnaval.negocio;

import java.util.Date;

import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;
import br.com.carnaval.repositorio.Fila;

public interface IControlador {
	public void inserir(String nome, int id, Date dataDeApresentacao, String cidade, Estilo estilo);

	public void remover(int id);

	public void atualizar(int id, String nome, int idNovo, Date dataDeApresentacao, String cidade, Estilo estilo);

	public BlocoDeCarnaval pesquisar(int id);

	public Fila<BlocoDeCarnaval> pesquisar(Date data);

	public Fila<BlocoDeCarnaval> pesquisar(Estilo estilo);

	public Fila<BlocoDeCarnaval> pesquisar();

	public Fila<BlocoDeCarnaval> pesquisar(String cidade);

}
