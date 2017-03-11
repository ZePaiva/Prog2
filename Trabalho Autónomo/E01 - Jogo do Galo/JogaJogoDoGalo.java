import static java.lang.System.*;
import java.util.Scanner;
import jogos.*;
    
public class JogaJogoDoGalo {
	public static void main(String[] args) {
		Scanner sin = new Scanner(in);
		char jogador1 = 'X';
		char jogador2 = 'O';
		int lin, col;
		int ties = 0;
		int wins1 = 0; //wins1 -> jogador1
		int wins2 = 0; //wins2 -> jogador2
		int nJogos = 0;

		do {
			
			JogoDoGalo jogo = new JogoDoGalo(jogador1, jogador2);
			System.out.printf("Jogo #%d\n", nJogos+1);

			jogo.mostraTabuleiro();   			
		
			while (!jogo.terminado()) {
				out.print("(lin col): ");
				lin = sin.nextInt();
				col = sin.nextInt();
				while(!jogo.jogadaValida(lin, col)){
					System.out.println("Posição não válida");
					out.print("(lin col): ");
					lin = sin.nextInt();
					col = sin.nextInt();
				}

				jogo.jogada(lin, col);
				jogo.mostraTabuleiro();
			}
			out.println();

			if (jogo.ultimoJogadorGanhou()){
				out.println("Jogador "+jogo.ultimoJogador()+" ganhou!");
				if (jogo.ultimoJogador() == jogador1) {
					wins1++;
				} else {
					wins2++;
				}
			} else {
				out.println("Jogo empatado!");
				ties++;
			}

			nJogos++;

			System.out.println("Jogador | Vitórias | Derrotas | Empates");
			System.out.printf("%4c    | %5d    | %5d    | %5d\n", jogador1, wins1, nJogos-wins1-ties, ties);
			System.out.printf("%4c    | %5d    | %5d    | %5d\n", jogador2, wins2, nJogos-wins2-ties, ties);	
		
		} while (nJogos < 10 && wins2 < 3 && wins1 < 3);

		if (wins1 == wins2) {
			System.out.println("Torneio empatado.\n");			
		} else {
			System.out.printf("O Jogador %c ganhou o torneio.\n\n", wins1 > wins2 ? jogador1 : jogador2 );
		}
	}
}
