package br.com.carnaval.negocio;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;

public interface IControlador {
	public void inserir(String nome, int id, Date dataDeApresentacao, String cidade, Estilo estilo);

	public void remover(int id);

	public void atualizar(int id, String nome, int idNovo, Date dataDeApresentacao, String cidade, Estilo estilo);

	public BlocoDeCarnaval pesquisar(int id);

	public Queue<BlocoDeCarnaval> pesquisar(Date data);

	public Queue<BlocoDeCarnaval> pesquisar(Estilo estilo);

}
