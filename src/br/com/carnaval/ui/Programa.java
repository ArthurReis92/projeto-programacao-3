package br.com.carnaval.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.Scanner;

import br.com.carnaval.excecoes.CNPJNaoCadastradoException;
import br.com.carnaval.negocio.ControladorBlocoDeCarnaval;
import br.com.carnaval.negocio.IControlador;
import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;

public class Programa {

	public static void main(String[] args) throws ParseException {
		Scanner leitor = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

		IControlador c = new ControladorBlocoDeCarnaval();
		char teste2 = 'n';
		do {
			System.out.println("1- Inserir\n2- Remover\n3- Atualizar\n4- Pesquisar\n5 - Sair");
			int opcao = leitor.nextInt();
			leitor.nextLine();
			char teste = 's';

			switch (opcao) {
			case 1:
				do {
					System.out.print("Digite o nome do bloco: ");
					String nome = leitor.nextLine();
					System.out.print("Digite o CNPJ: ");
					int id = leitor.nextInt();
					leitor.nextLine();
					System.out.print("Digite a data de apresenta��o (dd/MM/yyyy HH:mm): ");
					String dataApresentacao = leitor.nextLine();
					System.out.print("Digite o nome da cidade: ");
					String cidade = leitor.nextLine();
					System.out.print("Digite o estilo do estilo (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
					String estilo = leitor.nextLine().toUpperCase();
					c.inserir(nome, id, sdf.parse(dataApresentacao), cidade, Estilo.valueOf(estilo));

					System.out.println("Voc� deseja inserir um novo bloco? (s/n)");
					teste = leitor.next().charAt(0);
					leitor.nextLine();
				} while (teste == 's');
				break;
			case 2:
				do {
					System.out.print("Digite o CNPJ do bloco: ");
					int id = leitor.nextInt();
					c.remover(id);
				} while (teste == 's');
				break;
			case 3:
				do {
					System.out.print("Digite o novo nome do bloco: ");
					String nome = leitor.nextLine();
					System.out.print("Digite o novo CNPJ: ");
					int idNovo = leitor.nextInt();
					leitor.nextLine();
					System.out.print("Digite a nova data de apresenta��o (dd/MM/yyyy HH:mm): ");
					String dataApresentacao = leitor.nextLine();
					System.out.print("Digite o nome da cidade: ");
					String cidade = leitor.nextLine();
					System.out.print("Digite o novo estilo do bloco (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
					String estilo = leitor.nextLine().toUpperCase();

					System.out.println("Digite o cnpj do bloco que voc� deseja atualizar: ");
					int id = leitor.nextInt();
					c.atualizar(id, nome, idNovo, sdf.parse(dataApresentacao), cidade, Estilo.valueOf(estilo));

					System.out.println("Voc� deseja atualizar mais algum bloco? (s/n)");
					teste = leitor.next().charAt(0);
					leitor.nextLine();
				} while (teste == 's');
				break;

			case 4:
				do {
					
					System.out.println("1 - Pesquisar por data\n2 - Pesquisar por cnpj\n3 - Pesquisar por estilo");
					int op = leitor.nextInt();
					leitor.nextLine();
					switch (op) {
					case 1:
						System.out.print("Digite a data de apresenta��o (dd/MM/yyyy): ");
						String dataDeApresentacao = leitor.nextLine();
						Queue<BlocoDeCarnaval> apresentacoesDaData = c.pesquisar(sdf2.parse(dataDeApresentacao));
						int numeroDeBlocos = apresentacoesDaData.size();
						for (int i = 0; i < numeroDeBlocos; i++) {
							System.out.println(apresentacoesDaData.remove() + "\n");
						}
						break;
					case 2:
						boolean verificacao = true;
						while (verificacao) {
							try {
								System.out.print("Digite o CNPJ do bloco: ");
								int id = leitor.nextInt();
								BlocoDeCarnaval bloco = c.pesquisar(id);
								System.out.println(bloco);
								verificacao = false;
							} catch (CNPJNaoCadastradoException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					case 3:
						System.out.print("Digite o estilo musical desejado: ");
						String estilo = leitor.nextLine().toUpperCase();
						Queue<BlocoDeCarnaval> blocosEstilo = c.pesquisar(Estilo.valueOf(estilo));
						int tamanhoDaFila = blocosEstilo.size();
						for (int i = 0; i < tamanhoDaFila; i++) {
							System.out.println(blocosEstilo.remove() + "\n");
						}
						break;
					}
					System.out.print("Deseja realizar mais alguma pesquisa? (s/n) ");
					teste = leitor.next().charAt(0);
				} while (teste == 's');
				break;
			default:
				System.out.println("Op��o inv�lida!");
				break;
			}
			System.out.println("Deseja retornar ao menu principal: (s/n)");
			teste2 = leitor.next().charAt(0);
			leitor.nextLine();
		} while (teste2 == 's');
		
		leitor.close();
	}

}