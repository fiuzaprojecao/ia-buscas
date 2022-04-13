import espacoDeEstados.*;
import estrategiasDeBusca.cega.*;
import estrategiasDeBusca.heuristica.AStar;
import estrategiasDeBusca.heuristica.BestFirst;
import estrategiasDeBusca.heuristica.BuscaInformada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {
		
		//char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		//char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
		char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		char[] cfgFim = {'2','3',' ','7','4','1','5','8','6'};
		//char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory

		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );
			
		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado(cfgFim);
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);

		Scanner menu = new Scanner(System.in);

		while (true) {

			System.out.print("+-------------------------------------------------+\n");
			System.out.print("|                     Buscas                      |\n");
			System.out.print("+-------------------------------------------------+\n");
			System.out.print("| 1 - Busca em Largura     						|\n");
			System.out.print("| 2 - Busca em Profundidade          				|\n");
			System.out.print("| 3 - Busca em Profundidade Limitada          	|\n");
			System.out.print("| 4 - Busca em Profundidade Limitada Interativa   |\n");
			System.out.print("| 5 - Busca A*                                    |\n");
			System.out.print("| 6 - Busca Best First                            |\n");
			System.out.print("| 7 - Sair              							|\n");
			System.out.print("+-------------------------------------------------+\n");
			System.out.print("\n");
			System.out.print("Digite uma opção: ");

			int opcao = menu.nextInt();

			if (opcao == 7) {
				break;
			}

			switch (opcao) {
				case 1:
					buscaEmLargura(puzzleInicial, puzzleFinal);
					break;

				case 2:
					buscaEmProfundidade(puzzleInicial, puzzleFinal);
					break;

				case 3:
					buscaEmProfundidadeLimitada(puzzleInicial, puzzleFinal, 10);
					break;

				case 4:
					buscaEmProfundidadeLimitadaInterativa(puzzleInicial, puzzleFinal);
					break;

				case 5:
					buscaAEstrela(puzzleInicial, puzzleFinal);
					break;

				case 6:
					buscaBestFirst(puzzleInicial, puzzleFinal);
					break;

				default:
					System.out.print("\nOpção Inválida\n\n");
					break;
			}
		}
	}

	private static void buscaEmProfundidadeLimitada(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal, int limite) {
		BuscaEmProfundidadeLimitada buscaEmProfundidadeLimitada = new BuscaEmProfundidadeLimitada();
		buscaEmProfundidadeLimitada.setLimite(limite);
		buscaEmProfundidadeLimitada.setInicio(puzzleInicial);
		buscaEmProfundidadeLimitada.setObjetivo(puzzleFinal);
		buscaEmProfundidadeLimitada.buscar();

		printCaminho(buscaEmProfundidadeLimitada.getCaminhoSolucao());
	}

	private static void buscaEmProfundidade(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal) {
		BuscaCega buscaEmProfundidade = new BuscaEmProfundidade();
		buscaEmProfundidade.setInicio(puzzleInicial);
		buscaEmProfundidade.setObjetivo(puzzleFinal);
		buscaEmProfundidade.buscar();

		printCaminho(buscaEmProfundidade.getCaminhoSolucao());
	}

	private static void buscaEmProfundidadeLimitadaInterativa(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal) throws IOException {

		System.out.println("BUSCA EM PROFUNDIDADE LIMITADA INTERATIVA\n");

		System.out.println("Insira o valor Inicial");
		BufferedReader inputValorInicialBuffer = new BufferedReader(new InputStreamReader(System.in));
		int valorInicial = Integer.valueOf(inputValorInicialBuffer.readLine());

		System.out.println("Insira o valor Final");
		BufferedReader inputValorFinalBuffer = new BufferedReader(new InputStreamReader(System.in));
		int valorFinal = Integer.valueOf(inputValorFinalBuffer.readLine());

		for(int i = valorInicial; i <= valorFinal; i++){
			buscaEmProfundidadeLimitada(puzzleInicial, puzzleFinal, i);
		}
	}

	private static void buscaEmLargura(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal) {
		BuscaCega busca = new BuscaEmLargura();
		busca.setInicio(puzzleInicial);
		busca.setObjetivo(puzzleFinal);
		busca.buscar();

		printCaminho(busca.getCaminhoSolucao());
	}

	private static void buscaAEstrela(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal){
		AStar buscaAEstrela = new AStar();
		buscaAEstrela.setInicio(puzzleInicial);
		buscaAEstrela.setObjetivo(puzzleFinal);
		buscaAEstrela.buscar();

		printCaminho(buscaAEstrela.getCaminhoSolucao());
	}

	private static void buscaBestFirst(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal){
		BestFirst bestFirst = new BestFirst();
		bestFirst.setInicio(puzzleInicial);
		bestFirst.setObjetivo(puzzleFinal);

		bestFirst.buscar();

		printCaminho(bestFirst.getCaminhoSolucao());
	}

	private static void printCaminho(List<Estado<?>> caminhos) {
		for(Estado e : caminhos) {
			System.out.println(e);
		}
	}
}
