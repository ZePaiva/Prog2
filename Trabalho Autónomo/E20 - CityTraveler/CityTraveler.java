import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;
import p2utils.*;

public class CityTraveler{
	public static void main(String[] args) {
		assert args.length > 0 : "Precisa de pelo menos um argumento";

		HashTable tabela = new HashTable(10);
		for (int i = 0; i < args.length; i++) {
			HashTable newHash = tabela;
			try{
				Scanner kfix = new Scanner(new File(args[i]));
				while(kfix.hasNextLine()){
					String linha = (kfix.nextLine()).trim();
					LinkedList tmp;
					if (newHash.contains(linha)) {
						tmp = (LinkedList)newHash.get(linha);
					} else {
						tmp = new LinkedList();
						newHash.set(linha, tmp);
					}
					tmp.addLast(args[i]);
				}
				kfix.close();
			} catch (IOException e) {
				System.out.println("Erro no ficheiro " + args[i]);
				exit(0);
			}
		}

		String[] chaves = tabela.keys();

		// por causa de ser usada uma lista o strnig fica diferente
		// mas vai dar tudo ao mesmo
		for (int i = 0; i < chaves.length; i++) {
			System.out.printf("%-14s : ", chaves[i]);
			System.out.println((tabela.get(chaves[i])).toString());
		}
	}
}