import java.util.*;
import java.io.*;

public class E607 {

	public static void main(String[] args) {
		if(args.length  > 2) {
			System.err.println("excesso de argumentos");
			System.exit(1);
		}

		File fix;
		if(args.length == 2) {
			fix = new File(args[0]);
		} else {
			fix = new File(".");
			System.err.println("falta de argumentos");
			System.exit(1);
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

		listDirectoryName(fix, args);
		//listDirectoryInd(fix, args[1]);
	}

	public static void listDirectoryName(File ficheiro, String[] pattern) {
		assert ficheiro != null;

		File[] filesInDir = ficheiro.listFiles();

		for(int i = 0; i < filesInDir.length; i++) {
			
			if ((filesInDir[i].getName()).equals(pattern[1])) {
				String direito = "";
				String dirParent = filesInDir[i].getParent();
				if(dirParent != null && !dirParent.equals("..")) {
					direito += dirParent + "/";
				}

				direito = direito + filesInDir[i].getName();
				System.out.printf("%-19s\n", new Object[]{direito});

				if(filesInDir[i].isDirectory()) {
					listDirectoryName(filesInDir[i], pattern);
				}	
			}
		}
	}

	public static void listDirectoryInd(File ficheiro, String pattern) {
		assert ficheiro != null && ficheiro.exists();

		if(ficheiro.getName().indexOf(pattern) > -1) {
			System.out.println(ficheiro.getPath());
		}

		if(ficheiro.isDirectory() && ficheiro.canRead()) {
			File[] filesInDir = ficheiro.listFiles();

			for(int i = 0; i < filesInDir.length; ++i) {
				listDirectoryInd(filesInDir[i], pattern);
			}
		}
	}
}