package br.com.carnaval.repositorio;

import java.util.ArrayList;
import java.util.List;

import br.com.carnaval.excecoes.FilaVaziaException;

public class Fila<T> {
	private List<T> fila;

	public Fila() {
		fila = new ArrayList<T>();
	}

	public void inserir(T elemento) {
		fila.add(elemento);
	}

	public boolean estaVazia() {
		return fila.isEmpty();
	}

	public T remover() {
		if (!estaVazia()) {
			T elementoRemovido = fila.get(0);
			fila.remove(0);
			return elementoRemovido;
		} else {
			throw new FilaVaziaException("A fila de apresentação está vazia");
		}
	}

	public void limparTodos() {
		if (!estaVazia()) {
			fila.clear();
		} else {
			throw new FilaVaziaException("A fila de apresentação está vazia");
		}
	}

	public String imprimir() {
		String filaParaImprimir = "";
		if (!estaVazia()) {
			int posicao = 1;
			for (T elemento : fila) {
				filaParaImprimir += posicao + ".\n" + elemento.toString() + "\n\n";
				posicao++;
			}
			return filaParaImprimir;
		} else {
			throw new FilaVaziaException("A fila de apresentação está vazia");
		}
	}

	public int pegarTamanho() {
		return fila.size();
	}

	public boolean verificarExistencia(T element) {
		return fila.contains(element);
	}
}
