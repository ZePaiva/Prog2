import static java.lang.System.*;
import java.util.Scanner;   // => "error: reference to LinkedList is ambiguous"
import java.io.*;
import p2utils.*;

public class P71{

	public static void main(String[] args) throws IOException{
		if (args.length != 1) {
			err.printf("Usage: java -ea P71 text-file\n");
			exit(1);
		}
		
		File fil = new File(args[0]);

		// lista das linha menors que 20
		LinkedList<String> sub20 = new LinkedList<String>();

		// lista das linhas entre 20 e 40
		LinkedList<String> bet20and40 = new LinkedList<String>();

		// lista das linhas com mais de 40
		LinkedList<String> over40 = new LinkedList<String>();
		
		try{
			Scanner sf = new Scanner(fil);
			
			while (sf.hasNextLine()) {
				String line = sf.nextLine();

				if(line.length() < 20) {
					sub20.addLast(line);
				} else if(line.length() <= 40) {
					bet20and40.addLast(line);
				} else {
					over40.addLast(line);
				}
			}

			// Escrever conteúdo das listas...
			out.println("Curtas---|---------|---------|---------|---------");
			sub20.print();

			out.println("Médias---|---------|---------|---------|---------");
			bet20and40.print();

			out.println("Longas---|---------|---------|---------|---------");
			over40.print();

			sf.close();

		} catch (FileNotFoundException e){
			System.out.println("Ficheiro não encontrado");
			System.exit(2);
		}
	}
}