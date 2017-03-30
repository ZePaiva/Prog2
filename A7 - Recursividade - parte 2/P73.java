import static java.lang.System.*;
import java.io.*;

import p2utils.LinkedList;

public class P73 {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			out.println("Uso: java -ea P73 <dir>");
			exit(1);
		}

		File dir = new File(args[0]);

		// Verificar entrada
		if (!dir.exists()) {
			err.printf("ERRO: %s nao existe!\n", dir);
			exit(2);
		}

		LinkedList<File> files = recListFiles(dir);
		files.print();
	}

	/** Devolve uma lista com o conteúdo de um directório f
	*  e de todos os seus subdirectórios recursivamente.
	*/
	public static LinkedList<File> recListFiles(File f) throws IOException {
		assert f != null;

		LinkedList<File> diretorio = new LinkedList<File>();

	
		diretorio.addLast(f);
		if(f.exists() && f.isDirectory() && f.canRead()) {
			File[] secArray = f.listFiles();

			for(int i = 0; i < secArray.length; ++i) {
				diretorio = diretorio.concatenate(recListFiles(secArray[i]));
			}
		}
		
		return diretorio;
	}
}