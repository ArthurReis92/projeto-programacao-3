package br.com.carnaval.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.carnaval.excecoes.BancoDeCadastroVazioException;
import br.com.carnaval.excecoes.CNPJJaCadastradoException;
import br.com.carnaval.excecoes.NaoCadastradoException;
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
								System.err.println("Entrada inv�lida!");
								leitor.nextLine();
							}
						}

						Date dataApresentacao;
						while (true) {
							try {
								System.out.print("Digite a data de apresenta��o no padr�o dd/MM/aaaa HH:mm: ");
								dataApresentacao = sdf.parse(leitor.nextLine());
								break;
							} catch (ParseException e) {
								System.out.println("Formato de data inv�lido!");
							}
						}

						System.out.print("Digite o nome da cidade: ");
						String cidade = leitor.nextLine();

						Estilo estilo;
						while (true) {
							try {
								System.out.print("Digite o estilo do bloco (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
								estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
								break;
							} catch (IllegalArgumentException e) {
								System.out.println("Estilo inv�lido!");
							}
						}
						c.inserir(nome, id, dataApresentacao, cidade, estilo);

					} catch (CNPJJaCadastradoException e) {
						System.err.println(e.getMessage());
					}
					System.out.print("Voc� deseja inserir um novo bloco? (s/n) ");
					teste = leitor.next().charAt(0);
					leitor.nextLine();
				} while (teste == 's');
				break;

			case 2:
				System.out.println();
				System.out.println("------------------------");
				System.out.println("--- MENU DE REMO��O ---");
				System.out.println("------------------------");

				do {
					int id = -1;
					System.out.println();
					while (true) {
						try {
							System.out.print("Digite o CNPJ do bloco: ");
							id = leitor.nextInt();
							break;
						} catch (InputMismatchException e) {
							System.err.println("Entrada inv�lida!");
							leitor.nextLine();
						}
					}
					try {
						c.remover(id);
					} catch (BancoDeCadastroVazioException e) {
						System.out.println();
						System.err.println(e.getMessage());
						break;
					} catch (NaoCadastradoException e) {
						System.out.println();
						System.err.println(e.getMessage());
					}
					System.out.println();
					System.out.print("Deseja remover mais algum bloco? (s/n) ");
					teste = leitor.next().charAt(0);
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
					System.out.println();
					System.out.println("------------------------");
					System.out.println("--- MENU DE PESQUISA ---");
					System.out.println("------------------------");
					System.out.println(
							"1 - Pesquisar por data\n2 - Pesquisar por cnpj\n3 - Pesquisar por estilo\n4 - Pesquisar por cidade\n5 - Pesquisar todos");
					System.out.println("------------------------");
					int op = leitor.nextInt();
					System.out.println("------------------------");
					leitor.nextLine();
					System.out.println();
					switch (op) {
					case 1:
						System.out.println("-------------------------");
						System.out.println("--- PESQUISA POR DATA ---");
						System.out.println("-------------------------");
						Date dataDeApresentacao;
						while (true) {
							try {
								System.out.print("Digite a data de apresenta��o (dd/MM/yyyy): ");
								dataDeApresentacao = sdf2.parse(leitor.nextLine());
								break;
							} catch (ParseException e) {
								System.out.println();
								System.err.println("Formato de data inv�lido!");
							}
						}
						try {
							Fila<BlocoDeCarnaval> apresentacoesDaData = c.pesquisar(dataDeApresentacao);
							int numeroDeBlocos = apresentacoesDaData.pegarTamanho();
							System.out.println();
							System.out
									.println("--- TODOS OS BLOCOS DA DATA " + sdf2.format(dataDeApresentacao) + " ---");
							for (int i = 0; i < numeroDeBlocos; i++) {
								System.out.println(apresentacoesDaData.remover() + "\n");
							}
						} catch (NaoCadastradoException e) {
							System.out.println();
							System.err.println(e.getMessage());
						}

						break;

					case 2:
						System.out.println("-------------------------");
						System.out.println("--- PESQUISA POR CNPJ ---");
						System.out.println("-------------------------");
						boolean verificacao = true;
						int id = 0;
						while (verificacao) {
							try {
								System.out.print("Digite o CNPJ do bloco: ");
								id = leitor.nextInt();
								verificacao = false;
							} catch (InputMismatchException e) {
								System.err.println("Entrada inv�lida!");
								leitor.nextLine();
							}
						}
						try {
							BlocoDeCarnaval bloco = c.pesquisar(id);
							System.out.println();
							System.out.println("--- DADOS DO BLOCO DE CNPJ " + id + " ---\n");
							System.out.println(bloco);
						} catch (NaoCadastradoException e) {
							System.err.println(e.getMessage());
						}
						break;

					case 3:
						System.out.println("---------------------------");
						System.out.println("--- PESQUISA POR ESTILO ---");
						System.out.println("---------------------------");
						Estilo estilo;
						while (true) {
							try {
								System.out.print(
										"Digite o estilo musical desejado (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
								estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
								break;
							} catch (IllegalArgumentException e) {
								System.err.println("Estilo inv�lido!");
							}
						}
						try {
							Fila<BlocoDeCarnaval> blocosEstilo = c.pesquisar(estilo);
							int tamanhoDaFila = blocosEstilo.pegarTamanho();
							System.out.println();
							System.out.println("--- TODOS OS BLOCOS DO ESTILO MUSICAL " + estilo + " ---\n");
							for (int i = 0; i < tamanhoDaFila; i++) {
								System.out.println(blocosEstilo.remover() + "\n");
							}
						} catch (NaoCadastradoException e) {
							System.err.println(e.getMessage());
						}
						break;

					case 4:
						System.out.println("---------------------------");
						System.out.println("--- PESQUISA POR CIDADE ---");
						System.out.println("---------------------------");
						try {
							System.out.print("Digite o nome da cidade: ");
							String cidade = leitor.nextLine().toUpperCase();
							System.out.println();
							System.out.println("--- BLOCOS DA CIDADE " + cidade.toUpperCase() + " ---\n");
							System.out.println(c.pesquisar(cidade));
						} catch (NaoCadastradoException e) {
							System.err.println(e.getMessage());
						}
						break;
					case 5:
						System.out.println();
						System.out.println("--- TODOS OS BLOCOS CADASTRADOS ---");
						System.out.println(c.pesquisar());
						break;
					default:
						System.out.println("Op��o inv�lida!");
						break;
					}
					System.out.println();
					System.out.print("Deseja realizar mais alguma pesquisa? (s/n) ");
					teste = leitor.next().charAt(0);
				} while (teste == 's');
				break;
			case 5:
				teste2 = 'n';
				continue;
			default:
				System.out.println();
				System.out.println("Op��o inv�lida!");
				break;
			}
			System.out.println();
			System.out.print("Deseja retornar ao menu principal? (s/n) ");
			teste2 = leitor.next().charAt(0);
			leitor.nextLine();
		} while (teste2 == 's');
		System.out.println("Programa encerrado!");
		leitor.close();
	}

}
