package br.com.carnaval.repositorio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.com.carnaval.excecoes.CNPJNaoCadastradoException;
import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;

public class RepositorioBlocoDeCarnaval implements IRepositorio {
	private List<BlocoDeCarnaval> blocos = new ArrayList<BlocoDeCarnaval>();

	@Override
	public void inserir(BlocoDeCarnaval bloco) {
		blocos.add(bloco);
	}

	@Override
	public void remover(int id) {
		for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
			if (blocoDeCarnaval.getId() == id) {
				blocos.remove(blocoDeCarnaval);
				break;
			}
		}
	}

	@Override
	public void atualizar(int id, BlocoDeCarnaval bloco) {
		for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
			if (blocoDeCarnaval.getId() == id) {
				blocos.set(blocos.indexOf(blocoDeCarnaval), bloco);
				break;
			}
		}

	}

	@Override
	public BlocoDeCarnaval pesquisar(int id) {
		for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
			if (blocoDeCarnaval.getId() == id) {
				return blocoDeCarnaval;
			}
		}

		throw new CNPJNaoCadastradoException("Bloco não cadastrado!");
	}

	@Override
	public Queue<BlocoDeCarnaval> pesquisar(Date data) {
		Queue<BlocoDeCarnaval> blocosNaFila = new LinkedList<BlocoDeCarnaval>();
		List<BlocoDeCarnaval> blocosDaData = new ArrayList<BlocoDeCarnaval>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);

		Calendar cal2 = Calendar.getInstance();

		for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
			cal2.setTime(blocoDeCarnaval.getDataDeApresentacao());
			int diaBloco = cal2.get(Calendar.DAY_OF_MONTH);
			int mesBloco = cal2.get(Calendar.MONTH);
			if (diaBloco == dia && mesBloco == mes) {
				blocosDaData.add(blocoDeCarnaval);
			}
		}

		Sort.quickSort(0, blocosDaData.size() - 1, blocosDaData);

		for (int i = 0; i < blocosDaData.size(); i++) {
			blocosNaFila.add(blocosDaData.get(i));
		}

		return blocosNaFila;
	}

	@Override
	public Queue<BlocoDeCarnaval> pesquisar(Estilo estilo) {
		Queue<BlocoDeCarnaval> blocosNaFila = new LinkedList<BlocoDeCarnaval>();
		List<BlocoDeCarnaval> blocosDoEstilo = new ArrayList<BlocoDeCarnaval>();

		for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
			if (blocoDeCarnaval.getEstilo().equals(estilo)) {
				blocosDoEstilo.add(blocoDeCarnaval);
			}
		}

		Sort.quickSort(0, blocosDoEstilo.size() - 1, blocosDoEstilo);

		for (int i = 0; i < blocosDoEstilo.size(); i++) {
			blocosNaFila.add(blocosDoEstilo.get(i));
		}

		return blocosNaFila;
	}

	@Override
	public boolean verificarExistencia(int id) {

		for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
			if (blocoDeCarnaval.getId() == id) {
				return true;
			}
		}
		return false;
	}

}
