package br.com.carnaval.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.carnaval.excecoes.BancoDeCadastroVazioException;
import br.com.carnaval.excecoes.JaCadastradoException;
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
		char teste2 = 'N';

		System.out.println("---------------------------------------------------");
		System.out.println("---- SISTEMA DE CADASTRO DE BLOCOS DE CARNAVAL ----");
		System.out.println("---------------------------------------------------");
		System.out.println();
		do {
			System.out.println("------------------------");
			System.out.println("---- MENU PRINCIPAL ----");
			System.out.println("------------------------");
			System.out.println();
			System.out.println("------------------------");

			int opcao;
			char teste = 'S';
			while (true) {
				try {
					System.out.println("1- Cadastrar\n2- Remover\n3- Atualizar\n4- Pesquisar\n5- Sair");
					System.out.println("------------------------");
					opcao = leitor.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.err.println("------------------------");
					System.err.println("Entrada inválida!");
					System.err.println("------------------------");
					leitor.nextLine();
				}
			}
			leitor.nextLine();
			switch (opcao) {
			case 1:
				do {
					System.out.println();
					System.out.println("------------------------");
					System.out.println("--- MENU DE CADASTRO ---");
					System.out.println("------------------------");
					System.out.println();
					System.out.println("------------------------");
					System.out.print("Digite o nome do bloco: ");
					String nome = leitor.nextLine();
					System.out.println("------------------------");
					int id;
					while (true) {
						try {

							System.out.print("Digite o CNPJ: ");
							id = leitor.nextInt();
							break;
						} catch (InputMismatchException e) {
							System.err.println("------------------------");
							System.err.println("Entrada inválida!");
							System.err.println("------------------------");
							leitor.nextLine();
						}
					}
					leitor.nextLine();
					System.out.println("------------------------");
					Date dataApresentacao;
					while (true) {
						try {
							System.out.print("Digite a data de apresentação no padrão dd/MM/aaaa HH:mm: ");
							dataApresentacao = sdf.parse(leitor.nextLine());
							break;
						} catch (ParseException e) {
							System.err.println("------------------------");
							System.err.println("Formato de data inválido!");
							System.err.println("------------------------");
						}
					}

					System.out.println("------------------------");
					System.out.print("Digite o nome da cidade: ");
					String cidade = leitor.nextLine();
					System.out.println("------------------------");

					Estilo estilo;
					while (true) {
						try {
							System.out.print("Digite o estilo do bloco (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
							estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
							break;
						} catch (IllegalArgumentException e) {
							System.err.println("------------------------");
							System.err.println("Estilo inválido!");
							System.err.println("------------------------");
						}
					}

					try {
						c.inserir(nome, id, dataApresentacao, cidade, estilo);
					} catch (JaCadastradoException e) {
						System.err.println("------------------------");
						System.err.println(e.getMessage());
						System.err.println("------------------------");
					}

					System.out.println("------------------------");
					System.out.print("Você deseja inserir um novo bloco? (s/n) ");
					teste = leitor.next().toUpperCase().charAt(0);
					leitor.nextLine();
					System.out.println("------------------------");
				} while (teste == 'S');
				break;

			case 2:

				do {
					System.out.println();
					System.out.println("------------------------");
					System.out.println("--- MENU DE REMOÇÃO ---");
					System.out.println("------------------------");

					int id;
					System.out.println();
					System.out.println("------------------------");
					while (true) {
						try {
							System.out.print("Digite o CNPJ do bloco: ");
							id = leitor.nextInt();
							break;
						} catch (InputMismatchException e) {
							System.err.println("------------------------");
							System.err.println("Entrada inválida!");
							System.err.println("------------------------");
							leitor.nextLine();
						}
					}
					System.out.println("------------------------");
					try {
						String blocoRemovido = c.pesquisar(id).getNome();
						c.remover(id);
						System.out.println("Bloco " + blocoRemovido + " removido com sucesso!");
					} catch (BancoDeCadastroVazioException e) {
						System.err.println("------------------------");
						System.err.println(e.getMessage());
						System.err.println("------------------------");
						break;
					} catch (NaoCadastradoException e) {
						System.err.println("------------------------");
						System.err.println(e.getMessage());
						System.err.println("------------------------");
					}
					System.out.println("------------------------");
					System.out.println();
					System.out.print("Deseja remover mais algum bloco? (s/n) ");
					teste = leitor.next().toUpperCase().charAt(0);
				} while (teste == 'S');
				break;

			case 3:
				do {
					System.out.println();
					System.out.println("---------------------------");
					System.out.println("--- MENU DE ATUALIZAÇÃO ---");
					System.out.println("---------------------------");
					System.out.println();

					System.out.println("------------------------");
					int id;
					while (true) {
						try {
							System.out.print("Digite o cnpj do bloco que você deseja atualizar: ");
							id = leitor.nextInt();
							break;
						} catch (InputMismatchException e) {
							System.err.println("------------------------");
							System.err.println("Entrada inválida!");
							System.err.println("------------------------");
							leitor.nextLine();
						}
					}

					// Checando se o CNPJ informado está cadastrado
					try {
						c.pesquisar(id);
					} catch (BancoDeCadastroVazioException e) {
						System.err.println("------------------------");
						System.err.println(e.getMessage());
						System.err.println("------------------------");
						teste = 'N';
						continue;
					} catch (NaoCadastradoException e) {
						System.err.println("------------------------");
						System.err.println(e.getMessage());
						System.err.println("------------------------");
						char resp = 'N';
						System.out.print("Deseja realizar mais alguma atualização? (s/n) ");
						resp = leitor.next().toUpperCase().charAt(0);
						System.out.println("------------------------");
						if (resp == 'S') {
							continue;
						} else {
							teste = 'N';
							continue;
						}
					}

					leitor.nextLine();
					System.out.println("------------------------");
					System.out.print("Digite o novo nome do bloco: ");
					String nome = leitor.nextLine();

					System.out.println("------------------------");
					int idNovo;
					while (true) {
						try {
							System.out.print("Digite o novo CNPJ: ");
							idNovo = leitor.nextInt();
							break;
						} catch (InputMismatchException e) {
							System.err.println("------------------------");
							System.err.println("Entrada inválida!");
							System.err.println("------------------------");
							leitor.nextLine();
						}
					}

					leitor.nextLine();
					System.out.println("------------------------");
					Date dataApresentacao;
					while (true) {
						try {
							System.out.print("Digite a nova data de apresentação (dd/MM/yyyy HH:mm): ");
							dataApresentacao = sdf.parse(leitor.nextLine());
							break;
						} catch (ParseException e) {
							System.err.println("------------------------");
							System.err.println("Formato de data inválido!");
							System.err.println("------------------------");
						}
					}

					System.out.println("------------------------");
					System.out.print("Digite o novo nome da cidade: ");
					String cidade = leitor.nextLine();
					System.out.println("------------------------");

					Estilo estilo;
					while (true) {
						try {
							System.out.print("Digite o novo estilo do bloco (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
							estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
							break;
						} catch (IllegalArgumentException e) {
							System.err.println("------------------------");
							System.err.println("Estilo inválido!");
							System.err.println("------------------------");
						}
					}
					System.out.println("------------------------");

					try {
						c.atualizar(id, nome, idNovo, dataApresentacao, cidade, estilo);
						System.out.println("Atualização realizada com sucesso!");
					} catch (NaoCadastradoException e) {
						System.err.println("------------------------");
						System.err.println(e.getMessage());
						System.err.println("------------------------");
					}

					System.out.println("------------------------");

					System.out.print("Você deseja atualizar mais algum bloco? (s/n) ");
					teste = leitor.next().toUpperCase().charAt(0);
					leitor.nextLine();
				} while (teste == 'S');
				break;

			case 4:
				do {
					System.out.println();
					System.out.println("------------------------");
					System.out.println("--- MENU DE PESQUISA ---");
					System.out.println("------------------------");
					int op;
					
					while (true) {
						try {
							System.out.println(
									"1 - Pesquisar por data\n2 - Pesquisar por cnpj\n3 - Pesquisar por estilo\n4 - Pesquisar por cidade\n5 - Pesquisar todos");
							System.out.println("------------------------");
							op = leitor.nextInt();
							System.out.println("------------------------");
							break;
						} catch (InputMismatchException e) {
							System.err.println("------------------------");
							System.err.println("Entrada inválida!");
							System.err.println("------------------------");
							System.out.println();
							leitor.nextLine();
						}
					}
					
					leitor.nextLine();
					System.out.println();
					
					switch (op) {
					case 1:
						System.out.println("-------------------------");
						System.out.println("--- PESQUISA POR DATA ---");
						System.out.println("-------------------------");
						System.out.println();
						System.out.println("-------------------------");
						Date dataDeApresentacao;
						while (true) {
							try {
								System.out.print("Digite a data de apresentação (dd/MM/yyyy): ");
								dataDeApresentacao = sdf2.parse(leitor.nextLine());
								System.out.println("-------------------------");
								break;
							} catch (ParseException e) {
								System.err.println("------------------------");
								System.err.println("Formato de data inválido!");
								System.err.println("------------------------");
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
							System.out.println("-------------------------");
						} catch (NaoCadastradoException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						} catch (BancoDeCadastroVazioException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						}
						break;

					case 2:
						System.out.println("-------------------------");
						System.out.println("--- PESQUISA POR CNPJ ---");
						System.out.println("-------------------------");
						System.out.println();
						System.out.println("-------------------------");
						boolean verificacao = true;
						int id = 0;
						while (verificacao) {
							try {
								System.out.print("Digite o CNPJ do bloco: ");
								id = leitor.nextInt();
								verificacao = false;
								System.out.println("-------------------------");
							} catch (InputMismatchException e) {
								System.err.println("------------------------");
								System.err.println("Entrada inválida!");
								System.err.println("------------------------");
								leitor.nextLine();
							}
						}
						try {
							BlocoDeCarnaval bloco = c.pesquisar(id);
							System.out.println();
							System.out.println("--- DADOS DO BLOCO DE CNPJ " + id + " ---\n");
							System.out.println(bloco);
							System.out.println("------------------------");
						} catch (NaoCadastradoException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						} catch (BancoDeCadastroVazioException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						}
						break;

					case 3:
						System.out.println("---------------------------");
						System.out.println("--- PESQUISA POR ESTILO ---");
						System.out.println("---------------------------");
						System.out.println();
						System.out.println("---------------------------");
						Estilo estilo;
						while (true) {
							try {
								System.out.print(
										"Digite o estilo musical desejado (FREVO, MARACATU, CABOCLINHO, SAMBA): ");
								estilo = Estilo.valueOf(leitor.nextLine().toUpperCase());
								System.out.println("---------------------------");
								break;
							} catch (IllegalArgumentException e) {
								System.err.println("------------------------");
								System.err.println("Estilo inválido!");
								System.err.println("------------------------");
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
							System.out.println("------------------------");
						} catch (NaoCadastradoException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						} catch (BancoDeCadastroVazioException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						}
						break;

					case 4:
						System.out.println("---------------------------");
						System.out.println("--- PESQUISA POR CIDADE ---");
						System.out.println("---------------------------");
						System.out.println();
						System.out.println("---------------------------");
						try {
							System.out.print("Digite o nome da cidade: ");
							String cidade = leitor.nextLine().toUpperCase();
							System.out.println("---------------------------");
							System.out.println();

							Fila<BlocoDeCarnaval> blocosPorCidade = c.pesquisar(cidade);
							int tamanhoDaFila = blocosPorCidade.pegarTamanho();
							System.out.println("--- TODOS OS BLOCOS DA CIDADE " + cidade + " ---\n");
							for (int i = 0; i < tamanhoDaFila; i++) {
								System.out.println(blocosPorCidade.remover() + "\n");
							}
							System.out.println("------------------------");

						} catch (NaoCadastradoException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						} catch (BancoDeCadastroVazioException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						}
						break;
					case 5:
						System.out.println();
						System.out.println("------------------------------------------------");
						try {
							Fila<BlocoDeCarnaval> todosBlocos = c.pesquisar();
							int tamanhoDaFila = todosBlocos.pegarTamanho();
							System.out.println("--- TODOS OS BLOCOS NA ORDEM DE APRESENTAÇÃO ---\n\n");
							for (int i = 0; i < tamanhoDaFila; i++) {
								System.out.println(todosBlocos.remover() + "\n");
							}
							System.out.println("------------------------------------------------");
						} catch (BancoDeCadastroVazioException e) {
							System.err.println("------------------------");
							System.err.println(e.getMessage());
							System.err.println("------------------------");
						}
						break;
					default:
						System.out.println("---------------------------");
						System.out.println("Opção inválida!");
						System.out.println("---------------------------");
						break;
					}
					System.out.println();
					System.out.print("Deseja realizar mais alguma pesquisa? (s/n) ");
					teste = leitor.next().toUpperCase().charAt(0);
				} while (teste == 'S');
				break;
			case 5:
				teste2 = 'N';
				continue;
			default:
				System.out.println();
				System.out.println("Opção inválida!");
				break;
			}
			System.out.println();
			System.out.print("Deseja retornar ao menu principal? (s/n) ");
			teste2 = leitor.next().toUpperCase().charAt(0);
			leitor.nextLine();
			System.out.println();
		} while (teste2 == 'S');
		System.out.println("Programa encerrado!");
		leitor.close();
	}

}
