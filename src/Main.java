import espacoDeEstados.Estado;
import espacoDeEstados.Puzzle8;
import estrategiasDeBusca.cega.BuscaCega;
import estrategiasDeBusca.cega.BuscaEmLargura;
import estrategiasDeBusca.cega.BuscaEmProfundidade;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
import estrategiasDeBusca.heuristica.AStar;
import estrategiasDeBusca.heuristica.BestFirst;

import java.util.List;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		/*
		char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
		char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory
		*/

		char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );

		char[] cfgFim = {'1','2','3','4','5','6','7','8',' '};
		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado(cfgFim);
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);

		printMenu(puzzleInicial, puzzleFinal);
	}

	private static void printMenu(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal) {

		Scanner menu = new Scanner(System.in);

		while (true) {

			System.out.println("+-------------------------------------------------+");
			System.out.println("|                     Buscas                      |");
			System.out.println("+-------------------------------------------------+");
			System.out.println("| 1 - Busca em Largura     						  |");
			System.out.println("| 2 - Busca em Profundidade          			  |");
			System.out.println("| 3 - Busca em Profundidade Limitada          	  |");
			System.out.println("| 4 - Busca em Profundidade Limitada Interativa   |");
			System.out.println("| 5 - Busca A*                                    |");
			System.out.println("| 6 - Busca Best First                            |");
			System.out.println("| 7 - Sair              						  |");
			System.out.println("+-------------------------------------------------+");
			System.out.println();
			System.out.print("Digite uma opção: ");

			int opcao = menu.nextInt();

			if (opcao == 7) break;

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

	private static void buscaEmProfundidadeLimitadaInterativa(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal) {

		int index = 0;

		while (true) {
			try
			{
				System.out.println("Tentando com o limite: " + index);
				buscaEmProfundidadeLimitada(puzzleInicial, puzzleFinal, index);
				break;
			}
			catch (Exception e)
			{
				index++;
			}
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
