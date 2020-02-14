package br.com.carnaval.repositorio;

import java.util.Date;

import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;

public interface IRepositorio {
	public void inserir(BlocoDeCarnaval bloco);

	public void remover(int id);

	public void atualizar(int id, BlocoDeCarnaval bloco);

	public BlocoDeCarnaval pesquisar(int id);

	public Fila<BlocoDeCarnaval> pesquisar(Date data);

	public Fila<BlocoDeCarnaval> pesquisar(Estilo estilo);

	public boolean verificarExistencia(int id);
}
