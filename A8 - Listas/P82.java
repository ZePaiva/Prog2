import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class P82{
	
	public static void main(String[] args) throws IOException{
		
		if (args.length < 1) {
			System.out.println("Argumentos inválidos");
			System.exit(2);
		}

		//lista sorted
		SortedListInt lista = new SortedListInt();

		//array de ficheiros
		File[] fix = new File[args.length];

		for (int i = 0; i < fix.length; i++) {

			try{
				fix[i] = new File(args[i]);

				Scanner scFix = new Scanner(fix[i]);

				while (scFix.hasNext()) {
					String linha = scFix.next();

					try {
						int j = Integer.parseInt(linha);
						lista.insert(j);
					} catch (NumberFormatException e) {
						;
					}
				}

				System.out.printf("Lista do ficheiro %s:\n", args[i]);
				
				while (!lista.isEmpty()) {
					System.out.println(lista.first());
					lista.removeFirst();
				}
			} catch (FileNotFoundException e) {
				;
			}
		}
	}
}