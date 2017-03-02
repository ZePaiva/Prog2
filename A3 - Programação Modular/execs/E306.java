import e6.*;
import java.util.*;

public class E306{

	static final Scanner k = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opt;
		Data data = new Data();
		
		do{
			//menu
			System.out.println("\n\n\n1. Cria novo objeto com a data actual");
			System.out.println("2. Cria novo objeto com uma qualquer data");
			System.out.println("3. Indica se a data é válida");
			System.out.println("4. Escreve data");
			System.out.println("5. Escreve a data por extenso");
			System.out.println("6. Dia anterior");
			System.out.println("7. Dia seguinte");
			System.out.println("0. Termina");
			System.out.print("Opção: ");
			
			opt = k.nextInt();

			switch (opt) {
				case 0:
					break;
				
				case 1:
					data = new Data();
					break;

				case 2:
					System.out.print("Dia: ");
					int dia = k.nextInt();
					System.out.print("Mes: ");
					int mes = k.nextInt();
					System.out.print("Ano: ");
					int ano = k.nextInt();

					data = new Data(dia, mes, ano);

					break;

				case 3:
					if (data.tern()) {
						System.out.println("Data válida.");
					} else {
						System.out.println("Data inválida.");
					}

					break;

				case 4:
					data.printData();
					break;

				case 5:
					data.extenseDate();
					break;

				case 6:
					data.vaiParaOntem();
					break;

				case 7:
					data.vaiParaAmanha();
					break;

				default:
					System.out.println("Opção não válida.");
					break;
			}
		} while (opt != 0);
	}
}