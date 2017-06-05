import p2utils.HashTable;
import java.io.*;
import java.util.Scanner;

public class E1203{
	
	static final Scanner catIn = new Scanner(System.in);

	public static void main(String[] args) {
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

		while(catIn.hasNextLine()) {
			String num = catIn.nextLine();
			String numRep = num.replace('-', ' ');
			Scanner sc = new Scanner(numRep);

			for(boolean i = true; sc.hasNext(); i = false) {
					String number = sc.next();
					String numberExtense = number.toLowerCase();
					if(!i) {
							System.out.print(" ");
					}

					if(numbers.contains(numberExtense)) {
							number = (numbers.get(numberExtense)).toString();
					}

					System.out.print(number);
			}

			System.out.println();
		}
	}
}