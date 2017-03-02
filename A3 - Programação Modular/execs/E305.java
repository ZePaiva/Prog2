import e5.*;
import java.util.*;
import static java.lang.System.*;

public class E305{

	static final Scanner k = new Scanner(in);

	public static void main(String[] args) {
		
		int opt;
		Caixa caixa = new Caixa();

		do{
			System.out.println("1. Adicionar moedas");
			System.out.println("2. Retirar dinheiro");
			System.out.println("3. Ver moedas na carteira");
			System.out.println("4. Ver total da carteira");
			System.out.println("0. Termina");
			System.out.print("Opção: ");
			opt = k.nextInt();

			switch (opt) {
				case 0:
					break;
				case 1:
					System.out.print("Valor a adicionar: ");
					long moeda = k.nextInt();
					caixa.adicionaMoeda(moeda);
					System.out.println();
					break;
				case 2:
					System.out.print("Valor a retirar: ");
					long retiro = k.nextInt();

					//testa para ver se pode retirar troco
					if (caixa.daTroco(retiro)) {
						caixa.retiraDinheiro(retiro);	
					} else {
						System.out.println("Não é possível retirar a quantidade pedida");
					}
					System.out.println();
					break;
				case 3:
					System.out.println("Moedas:");
					long[] moedas = caixa.moedas();
					for (int i = 0; i < moedas.length; i++) {
						if (moedas[i] == 0) {
							break;
						}
						System.out.printf("%3d, ", moedas[i]);
					}
					System.out.println();
					System.out.println();
					break;
				case 4:
					System.out.printf("Total: %d\n", caixa.total());
					System.out.println();
					break;
				default:
					System.out.println("Opção não válida.");
					System.out.println();
					break;
			}
		}while (opt != 0);
	}
}