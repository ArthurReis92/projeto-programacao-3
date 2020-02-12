package br.com.carnaval.repositorio;

import java.util.Date;
import java.util.Queue;

import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;

public interface IRepositorio {
	public void inserir(BlocoDeCarnaval bloco);

	public void remover(int id);

	public void atualizar(int id, BlocoDeCarnaval bloco);

	public BlocoDeCarnaval pesquisar(int id);

	public Queue<BlocoDeCarnaval> pesquisar(Date data);

	public Queue<BlocoDeCarnaval> pesquisar(Estilo estilo);
	
	public boolean verificarExistencia(int id);
}
