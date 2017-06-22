import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;
import p2utils.*;

public class Restaurante {
	public static void main(String[] args) {
		assert args.length > 0 : "Precisa de pelo menos um ficheiro";
		// tabela com as comida existentes
		HashTable<Integer> comida = new HashTable<Integer>(10);
		// cada hashTable é um pedido, uma chave é o tipo e a int é a quantidade
		Queue<HashTable<Integer>> pedidos = new Queue<HashTable<Integer>>();

		for (int i = 0; i < args.length; i++) {
			readFile(args[i], comida, pedidos);
		}

		System.out.println("Comida em stock:");
		String[] comesLeft = comida.keys();
		for (int i = 0; i < comesLeft.length; i++) {
			if (comida.get(comesLeft[i]) > 0) {
				System.out.printf("\t %s:%d\n", comesLeft[i], comida.get(comesLeft[i]));
			}
		}

		while(!pedidos.isEmpty()){
			print("Refeição pendente: ", pedidos.peek());
			pedidos.out();
		}
	}

	public static void readFile(String fixWithReqs, HashTable<Integer> comida, Queue<HashTable<Integer>> pedidos){
		int lineNumb = 1;
		try{
			Scanner file = new Scanner(new File(fixWithReqs));
			while(file.hasNextLine()){
				String[] linha = ((file.nextLine()).trim()).split(" ");
				assert linha.length > 1 : "Erro no ficheiro na linha " + lineNumb;
				if (linha[0].equals("entrada:")) {
					int amountOf = 1;
					if (comida.contains(linha[1])) {
						amountOf += comida.get(linha[1]);
					}
					comida.set(linha[1], amountOf);
				} else if (linha[0].equals("saida:")) {
					HashTable<Integer> pedido = new HashTable<Integer>(10);
					for (int i = 1; i < linha.length; i++) {
						String[] comes = linha[i].split(":");
						assert comes.length == 2 : "ficheiro " + fixWithReqs + " com erro na linha " + lineNumb;
						assert !pedido.contains(comes[0]) : "alimento repetido na linha " + lineNumb;

						int amountOf = Integer.parseInt(comes[1]);
						pedido.set(comes[0], amountOf);
					}
					pedidos.in(pedido);
				}
				lineNumb++;
				serveOrNot(comida, pedidos);
			}
		} catch (IOException e) {
			System.out.println("Erro a ler um dos ficheiros que meteu praí");
			exit(0);
		} catch (NumberFormatException e) {
			System.out.printf("Erro com o número de ingredientes necessários na linha %d do ficheiro %s.\n", lineNumb, fixWithReqs);
			exit(0);
		}
	}

	public static void serveOrNot(HashTable<Integer> comida, Queue<HashTable<Integer>> pedidos){
		while (!pedidos.isEmpty()){
			HashTable<Integer> pedido = pedidos.peek();
			String[] aServir = pedido.keys();
			boolean podeServir = true;
			for (int i = 0; podeServir && i < aServir.length; i++) {
				if (!comida.contains(aServir[i])) {
					podeServir = false;
				} else {
					if (comida.get(aServir[i]) < pedido.get(aServir[i])) {
						podeServir = false;
					}
				}
			}

			if (podeServir) {
				print("Refeição servida: ", pedido);
				for (int i = 0; i < aServir.length; i++) {
					comida.set(aServir[i], comida.get(aServir[i]) - pedido.get(aServir[i]));
				}
				pedidos.out();
			} else {
				break;
			}
		}
	}

	public static void print(String yOn, HashTable<Integer> pedido){
		System.out.print(yOn);
		String[] ks = pedido.keys();
		for (int i = 0; i < ks.length; i++) {
			System.out.printf("%s:%d ", ks[i], pedido.get(ks[i]));
		}
		System.out.println();
	}
}