import static java.lang.System.*;
import java.util.Scanner;   // => "error: reference to LinkedList is ambiguous"
import java.io.*;
import p2utils.*;

public class E704{

	public static void main(String[] args) throws IOException{
		if (args.length != 1) {
			err.printf("Usage: java -ea P71 text-file\n");
			exit(1);
		}
		
		File fil = new File(args[0]);

		// lista das linha menors que 20
		LinkedList<String> sub20 = new LinkedList<String>();

		// lista das linhas com mais de 40
		LinkedList<String> over40 = new LinkedList<String>();
		
		try{
			Scanner sf = new Scanner(fil);
			
			while (sf.hasNextLine()) {
				String line = sf.nextLine();

				if(line.length() < 20) {
					sub20.addLast(line);
				} else if(line.length() > 40) {
					over40.addLast(line);
				}
			}

			LinkedList<String> invertida = sub20.reverse();
			LinkedList<String> l = invertida.concatenate(over40);

			// Escrever conteúdo das listas...
			out.println("Linhas---|---------|---------|---------|---------");
			l.print();

			sf.close();

		} catch (FileNotFoundException e){
			System.out.println("Ficheiro não encontrado");
			System.exit(2);
		}
	}
}