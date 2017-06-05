import p2utils.HashTable;
import java.io.*;
import java.util.Scanner;

public class E1204{
	
	static final Scanner catIn = new Scanner(System.in);

	public static void main(String[] args) {
		HashTable<Integer> numbers = readFile();

		/* para repetir caso haja mais números
		 * exemplo:
		 * two thousand and thirty three \n eight million two hundred thousand five hundred twenty-four \n one billion \n one million
		 * produz	-> two thousand and thirty three -> 2033
		 *					 eight million two hundred thousand five hundred twenty-four -> 8200524
		 *					 não existe número correspondente a : billion
		 *					 one million -> 1000000
		 */
		repetentes:
		while(catIn.hasNextLine()) {
			// numero para calculos
			int befAdd = 0;
			// resultado final
			int afterAdd = 0;
			// numero colocado
			String rawNumbers = catIn.nextLine();
			// numero colocado tratado
			String treatedNumbers = rawNumbers.replace('-', ' ').toLowerCase();
			// scanner de numeros tratados
			Scanner treatedScanner = new Scanner(treatedNumbers);

			while(true) {
				// string para analisar com os numeros tratados
				String toAnalyze;
				do {
					// quando acabar a análise de um numero por extenso repete
					if(!treatedScanner.hasNext()) {
						afterAdd += befAdd;
						System.out.println(rawNumbers + " -> " + afterAdd);
						continue repetentes;
					}
					toAnalyze = treatedScanner.next();
				} while(toAnalyze.equals("and"));

				// caso o par extenso-numerico não exista na tabela
				if(!numbers.contains(toAnalyze)) {
					System.err.printf("não existe número correspondente a : %s\n", toAnalyze);
					continue repetentes;
				}

				// obtem o valor numerico da palavra que está na string
				int numValue = numbers.get(toAnalyze);
				if(numValue <= befAdd) {
					afterAdd += befAdd;
					befAdd = 0;
				}

				// se é para ser multiplicado ou se é valor final
				// ex: two thousand -> 2000 = 2 * 1000
				// 		 two -> 2 = 2
				if(befAdd != 0 && numValue > befAdd) {
					befAdd *= numValue;
				} else {
					befAdd = numValue;
				}
			}
		}
	}

	public static HashTable<Integer> readFile(){
		HashTable<Integer> numbers = new HashTable(100);

		try{
			Scanner kb = new Scanner(new File("numbers.txt"));

			while (kb.hasNextLine()){
			
				// obtem uma linha e retira o \n
				String extNum = kb.nextLine().trim();
				if (extNum.length() > 0) {
			
					// separa entre caractere(s) e por extenso
					String[] numAndNum = extNum.split(" - ");
					if (numAndNum.length != 2){
						System.out.println("Erro no ficheiro");
						System.exit(0);
					}

					// coloca na tabela
					numbers.set(numAndNum[1].toLowerCase(), Integer.parseInt(numAndNum[0]));
				}
			}
			kb.close();

		} catch (NumberFormatException e) {
			System.out.println("Erro no ficheiro");
		} catch (IOException e) {
			System.out.println("Erro com o ficheiro");
		}
		return numbers;
	}
}