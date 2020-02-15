package br.com.carnaval.repositorio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.carnaval.excecoes.BancoDeCadastroVazioException;
import br.com.carnaval.excecoes.NaoCadastradoException;
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
		if (!blocos.isEmpty()) {
			for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
				if (blocoDeCarnaval.getId() == id) {
					blocos.remove(blocoDeCarnaval);
					return;
				}
			}
			throw new NaoCadastradoException("Bloco não cadastrado!");
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}
	}

	@Override
	public void atualizar(int id, BlocoDeCarnaval bloco) {
		if (!blocos.isEmpty()) {
			for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
				if (blocoDeCarnaval.getId() == id) {
					blocos.set(blocos.indexOf(blocoDeCarnaval), bloco);
					return;
				}
			}
			throw new NaoCadastradoException("O Bloco não pode ser atualizado pois não foi cadastrado!");
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}

	}

	@Override
	public BlocoDeCarnaval pesquisar(int id) {
		if (!blocos.isEmpty()) {
			for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
				if (blocoDeCarnaval.getId() == id) {
					return blocoDeCarnaval;
				}
			}
			throw new NaoCadastradoException("Bloco não cadastrado!");
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}
	}

	@Override
	public Fila<BlocoDeCarnaval> pesquisar(Date data) {
		if (!blocos.isEmpty()) {
			Fila<BlocoDeCarnaval> blocosNaFila = new Fila<BlocoDeCarnaval>();
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
			if (!blocosDaData.isEmpty()) {
				Sort.quickSort(0, blocosDaData.size() - 1, blocosDaData);

				for (int i = 0; i < blocosDaData.size(); i++) {
					blocosNaFila.inserir(blocosDaData.get(i));
				}

				return blocosNaFila;
			} else {
				throw new NaoCadastradoException("Não há blocos cadastrados para a data informada!");
			}
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}
	}

	@Override
	public Fila<BlocoDeCarnaval> pesquisar(Estilo estilo) {
		if (!blocos.isEmpty()) {
			Fila<BlocoDeCarnaval> blocosNaFila = new Fila<BlocoDeCarnaval>();
			List<BlocoDeCarnaval> blocosDoEstilo = new ArrayList<BlocoDeCarnaval>();

			for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
				if (blocoDeCarnaval.getEstilo().equals(estilo)) {
					blocosDoEstilo.add(blocoDeCarnaval);
				}
			}
			if (!blocosDoEstilo.isEmpty()) {
				Sort.quickSort(0, blocosDoEstilo.size() - 1, blocosDoEstilo);

				for (int i = 0; i < blocosDoEstilo.size(); i++) {
					blocosNaFila.inserir(blocosDoEstilo.get(i));
				}

				return blocosNaFila;
			} else {
				throw new NaoCadastradoException("Não há blocos cadastrados para o estilo informado!");
			}
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}

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

	@Override
	public Fila<BlocoDeCarnaval> pesquisar() {
		if (!blocos.isEmpty()) {
			Fila<BlocoDeCarnaval> blocosNaFila = new Fila<BlocoDeCarnaval>();
			List<BlocoDeCarnaval> blocosOrdenados = blocos;
			Sort.quickSort(0, blocosOrdenados.size() - 1, blocosOrdenados);

			for (int i = 0; i < blocosOrdenados.size(); i++) {
				blocosNaFila.inserir(blocosOrdenados.get(i));
			}

			return blocosNaFila;
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}
	}

	@Override
	public Fila<BlocoDeCarnaval> pesquisar(String cidade) {
		if (!blocos.isEmpty()) {
			Fila<BlocoDeCarnaval> blocosNaFila = new Fila<BlocoDeCarnaval>();
			List<BlocoDeCarnaval> blocosDaCidade = new ArrayList<BlocoDeCarnaval>();

			for (BlocoDeCarnaval blocoDeCarnaval : blocos) {
				if (blocoDeCarnaval.getCidade().toUpperCase().equals(cidade)) {
					blocosDaCidade.add(blocoDeCarnaval);
				}
			}
			if (!blocosDaCidade.isEmpty()) {
				Sort.quickSort(0, blocosDaCidade.size() - 1, blocosDaCidade);

				for (int i = 0; i < blocosDaCidade.size(); i++) {
					blocosNaFila.inserir(blocosDaCidade.get(i));
				}

				return blocosNaFila;
			} else {
				throw new NaoCadastradoException("Não há blocos para a cidade informada!");
			}
		} else {
			throw new BancoDeCadastroVazioException("Não há blocos cadastrados para o carnaval!");
		}
	}

}
