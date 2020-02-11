package br.com.carnaval.repositorio;

import java.util.List;

import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;

public class Sort {
	public static void quickSort(int idxInicio, int idxFim, List<BlocoDeCarnaval> blocos) {
		int i = idxInicio;
		int j = idxFim;
		int pivot = + (idxInicio + idxFim) / 2;

		while (i <= j) {
			while (blocos.get(i).getDataDeApresentacao().getTime() < blocos.get(pivot).getDataDeApresentacao().getTime()) {
				i++;
			}
			while (blocos.get(j).getDataDeApresentacao().getTime() > blocos.get(pivot).getDataDeApresentacao().getTime()) {
				j--;
			}
			if (i <= j) {
				change(i, j, blocos);
				i++;
				j--;
			}
		}
		if(i < idxFim) {
			quickSort(i, idxFim, blocos);
		}
		if(j > idxInicio) {
			quickSort(idxInicio, j, blocos);
		}
	}

//	public static int compare(BlocoDeCarnaval bloco, BlocoDeCarnaval blocoPivo) {
//		int resultado = 0;
//		if (bloco.getDataDeApresentacao().getTime() < blocoPivo.getDataDeApresentacao().getTime()) {
//			resultado = -1;
//		} else if (bloco.getDataDeApresentacao().getTime() > blocoPivo.getDataDeApresentacao().getTime()) {
//			resultado = 1;
//		}
//		return resultado;
//	}

	public static void change(int i, int j, List<BlocoDeCarnaval> blocos) {
		BlocoDeCarnaval aux = blocos.get(i);
		blocos.set(i, blocos.get(j));
		blocos.set(j, aux);
	}

}
