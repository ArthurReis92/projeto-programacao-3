package br.com.carnaval.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import br.com.carnaval.negocio.ControladorBlocoDeCarnaval;
import br.com.carnaval.negocio.IControlador;
import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;
import br.com.carnaval.repositorio.Fila;

public class Teste {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Scanner leitor = new Scanner(System.in);
		// SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

//		Date d = sdf.parse("22/02/2020 09:00:00");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(d);
//		System.out.println(cal.get(Calendar.DAY_OF_MONTH));

//		Queue<Integer> fila = new LinkedList<Integer>();
//		
//		fila.add(1);
//		fila.add(2);
//		fila.add(3);
//		fila.add(4);
//		fila.add(5);
//		fila.add(6);
//		fila.add(7);
//		System.out.println(fila.remove());
//		
//		List<BlocoDeCarnaval> lista = Arrays.asList(new BlocoDeCarnaval("A", "1", sdf.parse("22/02/2020 09:00"), "Recife", Estilo.FREVO),
//				new BlocoDeCarnaval("B", "2", sdf.parse("22/02/2020 08:00"), "Recife", Estilo.MARACATU),
//				new BlocoDeCarnaval("C", "3", sdf.parse("22/02/2020 07:00"), "Recife", Estilo.FREVO),
//				new BlocoDeCarnaval("D", "3", sdf.parse("22/02/2020 05:00"), "Recife", Estilo.FREVO));
//		for (BlocoDeCarnaval blocoDeCarnaval : lista) {
//			System.out.println(blocoDeCarnaval);
//		}
//		System.out.println("-----------------------------");
//		
//		Sort.quickSort(0, lista.size() - 1, lista);
//		for (BlocoDeCarnaval blocoDeCarnaval : lista) {
//			System.out.println(blocoDeCarnaval);
//		}
//		System.out.println("_____________________________");
//		
//		
		IControlador c = new ControladorBlocoDeCarnaval();
		c.inserir("A", 1, sdf.parse("22/02/2020 09:00"), "Recife", Estilo.FREVO);
		c.inserir("B", 2, sdf.parse("22/02/2020 08:00"), "Recife", Estilo.MARACATU);
		c.inserir("C", 3, sdf.parse("22/02/2020 07:00"), "Recife", Estilo.FREVO);
		c.inserir("D", 4, sdf.parse("22/02/2020 05:00"), "Recife", Estilo.FREVO);

		// int id = 2;
		// c.remover(id);

		// c.atualizar(1, "D", 5, sdf.parse("22/02/2020 13:00"), "Recife",
		// Estilo.CABOCLINHO);

//		Date data = sdf2.parse("22/02/2020");
//		Queue<BlocoDeCarnaval> apresentacoesDaData = c.pesquisar(data);
//		int numeroDeBlocos = apresentacoesDaData.size();
//		for (int i = 0; i < numeroDeBlocos; i++) {
//			System.out.println(apresentacoesDaData.remove() + "\n");
//		}
//		boolean verificacao = true;
//		while (verificacao) {
//			try {
//				System.out.print("Digite o CNPJ do bloco: ");
//				int id = leitor.nextInt();
//				BlocoDeCarnaval bloco = c.pesquisar(id);
//				System.out.println(bloco);
//				verificacao = false;
//			} catch (CNPJNaoCadastradoException e) {
//				System.out.println(e.getMessage());
//			}
//		}
		Estilo estilo;
		while (true) {
			try {
				System.out.print(
						"Digite o estilo musical desejado (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
				estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("Estilo inválida!");
			}
		}
		Fila<BlocoDeCarnaval> blocosEstilo = c.pesquisar(estilo);
		int tamanhoDaFila = blocosEstilo.pegarTamanho();
		System.out.println("--- TODOS OS BLOCOS DO ESTILO MUSICAL " + estilo + " ---\n");
		for (int i = 0; i < tamanhoDaFila; i++) {
			System.out.println(blocosEstilo.remover() + "\n");
		}
		
		System.out.println("Digite o nome da cidade");
		String cidade = leitor.nextLine();
		System.out.println();
		System.out.println("--- BLOCOS DA CIDADE " + cidade.toUpperCase() + " ---\n");
		System.out.println(c.pesquisar(cidade));
		
//		System.out.print("Digite o estilo musical desejado: ");
//		String estilo = leitor.nextLine().toUpperCase();
//		Fila<BlocoDeCarnaval> blocosEstilo = c.pesquisar(Estilo.valueOf(estilo));
//		int tamanhoDaFila = blocosEstilo.pegarTamanho();
//		for (int i = 0; i < tamanhoDaFila; i++) {
//			System.out.println(blocosEstilo.remover() + "\n");
//		}

//		String data = "22/02";
//		Queue<BlocoDeCarnaval> apresentacao = c.pesquisar(sdf2.parse(data));
//		int n = apresentacao.size();
//		System.out.println("---- Blocos organizados por horário de apresentação do dia " + data + " ----");
//		for (int i = 0; i < n; i++) {
//			System.out.println(apresentacao.remove() + "\n");
//		}
//		System.out.println("_____________________________");
//		String estilo = "FREVO";
//		System.out.println("---- Blocos organizados por horário de apresentação do estilo musical " + estilo + " ----");
//		apresentacao = c.pesquisar(Estilo.valueOf(estilo));
//		n = apresentacao.size();
//		for (int i = 0; i < n; i++) {
//			System.out.println("#Bloco " + (i+1));
//			System.out.println(apresentacao.remove() + "\n");
//		}

		leitor.close();
	}

}
