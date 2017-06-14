import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;
import p2utils.*;

public class JustifiedText{
	public static void main(String[] args) {
		if(args.length != 2) {
			System.err.println("Usage: java -ea JustifiedText <NUM-COLUMNS> <FILE>");
			System.exit(1);
		}

		int maxLineCols = 0;

		try {
			maxLineCols = Integer.valueOf(args[0]);
			assert maxLineCols > 0 : "Primeiro argumento tem de ser um número maior que zero";
		} catch (NumberFormatException e) {
			System.err.println("Primeiro argumento tem de ser um número");
			System.exit(2);
		}

		try {
			Scanner readFile = new Scanner(new File(args[1]));
			Queue<String> linha = new Queue<String>();
			int colsDestaLinha = 0;

			while(readFile.hasNextLine()) {
				String linhaDoFicheiro;
				if((linhaDoFicheiro = readFile.nextLine().trim()).length() == 0) {
					printQueue(linha);
					System.out.println();
					colsDestaLinha = 0;
				} else {
					Scanner linhaATratar = new Scanner(linhaDoFicheiro);

					while(linhaATratar.hasNext()) {
						String palavraDaLinhaAnalizar;
						if((palavraDaLinhaAnalizar = linhaATratar.next()).length() > maxLineCols || colsDestaLinha + palavraDaLinhaAnalizar.length() + linha.size() > maxLineCols) {
							colsDestaLinha = maxLineCols - colsDestaLinha;
							double spaces;
							if(linha.size() > 1) {
								spaces = (double)colsDestaLinha / (double)(linha.size() - 1);
							} else {
								spaces = 1.0D;
							}

							if(!linha.isEmpty()) {
								System.out.print(linha.peek());
								linha.out();
							}

							double espacoAPor = 0.0D;

							while(true) {
								if(linha.isEmpty()) {
									System.out.println();
									colsDestaLinha = 0;
									break;
								}

								double putSpaces = espacoAPor;
								espacoAPor += spaces;

								for(int i = 0; i < (int)espacoAPor - (int)putSpaces; i++, colsDestaLinha-- ) {
									System.out.print(' ');
								}

								if(linha.size() == 1) {
									while(colsDestaLinha > 0) {
										System.out.print(' ');
										colsDestaLinha--;
									}
								}

								System.out.print((String)linha.peek());
								linha.out();
							}
						}

						colsDestaLinha += palavraDaLinhaAnalizar.length();
						linha.in(palavraDaLinhaAnalizar);
					}
					linhaATratar.close();
				}
			}

			readFile.close();
			printQueue(linha);
		} catch (IOException e) {
				System.err.println("Erro ao ler o ficheiro");
				System.exit(1);
		}
	}

	private static void printQueue(Queue<String> fila) {
		if(!fila.isEmpty()) {
			System.out.print(fila.peek());
			fila.out();

			while(!fila.isEmpty()) {
				System.out.print(" " + fila.peek());
				fila.out();
			}

			System.out.println();
		}
	}
}