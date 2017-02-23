import static java.lang.System.*;
import static java.lang.Math.*;
import java.util.*;

public class E106 {

	public static void main(String[] args) {
		
		Scanner k = new Scanner(in);

		int secret, num, tentativas;
		secret = (int)(random() * 100) + 1;
		num = tentativas = 0;

		System.out.println("Jogo do adivinha o número (agora de 0 a 100).");
		System.out.println("---------------------------------------------");

		do {

			System.out.print("\nColoque um número: ");
			num = k.nextInt();
			System.out.println();

			if (num > secret) {
				
				System.out.printf("O número %d é maior do que o número escolhido.\nTenta outra vez.\n", num);

			} else if (num < secret) {
				
				System.out.printf("O número %d é menor do que o número escolhido.\nTenta outra vez.\n", num);
			}

			++tentativas;
			
		} while(num != secret);

		System.out.printf("Parabéns, ao fim de %d tentativas conseguiste acertar no número.\n", tentativas);
	}
}