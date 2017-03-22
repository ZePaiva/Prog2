import java.util.*;
import java.io.*;

public class E605 {

	public static void main(String[] args) {
		if(args.length > 1) {
			System.err.println("excesso de argumentos");
			System.exit(1);
		}

		File fix;
		if(args.length == 1) {
			fix = new File(args[0]);
		} else {
			fix = new File(".");
		}

		if(!fix.exists()) {
			System.err.println("diretório não encontrado");
			System.exit(2);
		}

		if(!fix.isDirectory()) {
			System.err.println("não é diretório");
			System.exit(3);
		}

		if(!fix.canRead()) {
			System.err.println("não dá para ler");
			System.exit(4);
		}

		listDirectory(fix);
	}

	public static void listDirectory(File ficheiro) {
		assert ficheiro != null;

		File[] filesInDir = ficheiro.listFiles();

		for(int i = 0; i < filesInDir.length; i++) {
			String direito = "";
			String dirParent = filesInDir[i].getParent();
			if(dirParent != null && !dirParent.equals("..")) {
				direito += dirParent + "/";
			}

			direito = direito + filesInDir[i].getName();
			System.out.printf("%-19s\n", new Object[]{direito});
			
			if(filesInDir[i].isDirectory()) {
				listDirectory(filesInDir[i]);
			}
		}
	}
}