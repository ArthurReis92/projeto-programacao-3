package br.com.carnaval.negocio;

import java.util.Date;

import br.com.carnaval.excecoes.CNPJJaCadastradoException;
import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;
import br.com.carnaval.repositorio.Fila;
import br.com.carnaval.repositorio.IRepositorio;
import br.com.carnaval.repositorio.RepositorioBlocoDeCarnaval;

public class ControladorBlocoDeCarnaval implements IControlador {
	private IRepositorio rptBlocoDeCarnaval;

	public ControladorBlocoDeCarnaval() {
		this.rptBlocoDeCarnaval = new RepositorioBlocoDeCarnaval();
	}

	public void remover(int id) {
		rptBlocoDeCarnaval.remover(id);
	}

	public void atualizar(int id, String nome, int idNovo, Date dataDeApresentacao, String cidade, Estilo estilo) {
		BlocoDeCarnaval bloco = new BlocoDeCarnaval(nome, idNovo, dataDeApresentacao, cidade, estilo);
		rptBlocoDeCarnaval.atualizar(id, bloco);
	}

	public BlocoDeCarnaval pesquisar(int id) {
		return rptBlocoDeCarnaval.pesquisar(id);
	}

	public Fila<BlocoDeCarnaval> pesquisar(Date data) {
		return rptBlocoDeCarnaval.pesquisar(data);
	}

	@Override
	public Fila<BlocoDeCarnaval> pesquisar(Estilo estilo) {
		return rptBlocoDeCarnaval.pesquisar(estilo);
	}

	@Override
	public void inserir(String nome, int id, Date dataDeApresentacao, String cidade, Estilo estilo) {

		if (!rptBlocoDeCarnaval.verificarExistencia(id)) {
			BlocoDeCarnaval bloco = new BlocoDeCarnaval(nome, id, dataDeApresentacao, cidade, estilo);
			rptBlocoDeCarnaval.inserir(bloco);
		} else {
			throw new CNPJJaCadastradoException("O CNPJ digitado já foi cadastrado!");
		}

	}

}
