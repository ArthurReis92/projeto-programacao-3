package br.com.carnaval.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.carnaval.excecoes.CNPJJaCadastradoException;
import br.com.carnaval.excecoes.CNPJNaoCadastradoException;
import br.com.carnaval.negocio.ControladorBlocoDeCarnaval;
import br.com.carnaval.negocio.IControlador;
import br.com.carnaval.negocio.entidades.BlocoDeCarnaval;
import br.com.carnaval.negocio.entidades.Estilo;
import br.com.carnaval.repositorio.Fila;

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
					try {
						System.out.print("Digite o nome do bloco: ");
						String nome = leitor.nextLine();
						int id;

						while (true) {
							try {
								System.out.print("Digite o CNPJ: ");
								id = leitor.nextInt();
								leitor.nextLine();
								break;
							} catch (InputMismatchException e) {
								System.err.println("Entrada inválida!");
								leitor.nextLine();
							}
						}

						Date dataApresentacao;
						while (true) {
							try {
								System.out.print("Digite a data de apresentação no padrão dd/MM/aaaa HH:mm: ");
								dataApresentacao = sdf.parse(leitor.nextLine());
								break;
							} catch (ParseException e) {
								System.out.println("Formato de data inválido!");
							}
						}

						System.out.print("Digite o nome da cidade: ");
						String cidade = leitor.nextLine();

						Estilo estilo;
						while (true) {
							try {
								System.out.print("Digite o estilo do estilo (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
								estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
								break;
							} catch (IllegalArgumentException e) {
								System.out.println("Estilo inválida!");
							}
						}
						c.inserir(nome, id, dataApresentacao, cidade, estilo);

					} catch (CNPJJaCadastradoException e) {
						System.err.println(e.getMessage());
					}
					System.out.println("Você deseja inserir um novo bloco? (s/n)");
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
					System.out.print("Digite a nova data de apresentação (dd/MM/yyyy HH:mm): ");
					String dataApresentacao = leitor.nextLine();
					System.out.print("Digite o nome da cidade: ");
					String cidade = leitor.nextLine();
					System.out.print("Digite o novo estilo do bloco (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
					String estilo = leitor.nextLine().toUpperCase();

					System.out.println("Digite o cnpj do bloco que você deseja atualizar: ");
					int id = leitor.nextInt();
					c.atualizar(id, nome, idNovo, sdf.parse(dataApresentacao), cidade, Estilo.valueOf(estilo));

					System.out.println("Você deseja atualizar mais algum bloco? (s/n)");
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
						System.out.print("Digite a data de apresentação (dd/MM/yyyy): ");
						String dataDeApresentacao = leitor.nextLine();
						Fila<BlocoDeCarnaval> apresentacoesDaData = c.pesquisar(sdf2.parse(dataDeApresentacao));
						int numeroDeBlocos = apresentacoesDaData.pegarTamanho();
						for (int i = 0; i < numeroDeBlocos; i++) {
							System.out.println(apresentacoesDaData.remover() + "\n");
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
						Fila<BlocoDeCarnaval> blocosEstilo = c.pesquisar(Estilo.valueOf(estilo));
						int tamanhoDaFila = blocosEstilo.pegarTamanho();
						for (int i = 0; i < tamanhoDaFila; i++) {
							System.out.println(blocosEstilo.remover() + "\n");
						}
						break;
					}
					System.out.print("Deseja realizar mais alguma pesquisa? (s/n) ");
					teste = leitor.next().charAt(0);
				} while (teste == 's');
				break;
			case 5:
				teste2 = 'n';
				continue;
			default:
				System.out.println("Opção inválida!");
				break;
			}
			System.out.println("Deseja retornar ao menu principal: (s/n)");
			teste2 = leitor.next().charAt(0);
			leitor.nextLine();
		} while (teste2 == 's');
		System.out.println("Programa encerrado!");
		leitor.close();
	}

}
