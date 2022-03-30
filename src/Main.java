import espacoDeEstados.*;
import estrategiasDeBusca.cega.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

		buscaEmLargura(puzzleInicial, puzzleFinal);
		buscaEmProfundidade(puzzleInicial, puzzleFinal);
		buscaEmProfundidadeLimitada(puzzleInicial, puzzleFinal, 10);
		buscaEmProfundidadeLimitadaInterativa(puzzleInicial, puzzleFinal);

		System.exit(0);
	}

	private static void buscaEmProfundidadeLimitada(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal, int limite) {
		BuscaEmProfundidadeLimitada buscaEmProfundidadeLimitada = new BuscaEmProfundidadeLimitada();
		buscaEmProfundidadeLimitada.setLimite(limite);
		buscaEmProfundidadeLimitada.setInicio(puzzleInicial);
		buscaEmProfundidadeLimitada.setObjetivo(puzzleFinal);
		buscaEmProfundidadeLimitada.buscar();
		for(Estado e : buscaEmProfundidadeLimitada.getCaminhoSolucao()) {
			System.out.println(e);
		}
	}

	private static void buscaEmProfundidade(Puzzle8 puzzleInicial, Puzzle8 puzzleFinal) {
		BuscaCega buscaEmProfundidade = new BuscaEmProfundidade();
		buscaEmProfundidade.setInicio(puzzleInicial);
		buscaEmProfundidade.setObjetivo(puzzleFinal);
		buscaEmProfundidade.buscar();
		for(Estado e : buscaEmProfundidade.getCaminhoSolucao()) {
			System.out.println(e);
		}
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
		for(Estado e : busca.getCaminhoSolucao()) {
			System.out.println(e);
		}
	}

}
