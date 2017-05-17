import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import p2utils.KeyValueList;

public class P112
{

  public static void main(String[] args) throws IOException {
    KeyValueList<Integer> counts = new KeyValueList<>();

    for (int a = 0; a < args.length; a++) { // Processa cada ficheiro
      File fin = new File(args[a]);
      if (fin.exists() && fin.canRead()) {
        Scanner scf = new Scanner(fin);
        while (scf.hasNextLine()) { // Processa cada linha
          String line = scf.nextLine();
          // Divide a linha em "palavras", considerando como separador
          // qualquer sequência de 1 ou mais carateres não alfanuméricos:
          String[] palavras = line.split("[^\\p{Alnum}]+");
          for (int i = 0; i < palavras.length; i++) { // Processa cada palavra
			String word = palavras[i].toLowerCase();
			int nums = 0;
			if (counts.contains(word)) {
				nums = counts.get(word);
				counts.set(word, ++nums);
			} else {
				counts.set(word, 1);
			}
          }
        }
        scf.close();
      }
      else {
        err.println("ERRO: Nao foi possivel ler do ficheiro "+args[a]);
      }
    }
    out.println(counts.toString("Results:\n", ";\n", "\nEnd"));
    out.println(counts.toString()); // mesma lista, outro formato
    mostFrequent(counts);
  }

  // Find and print the key with most occurrences
  // and its relative frequency.
  static void mostFrequent(KeyValueList<Integer> counts) {
	int total = 0;
	int relFreq = 0;
	
	String most = "";
	String[] palavras = counts.keys();

	for (int i = 0; i < palavras.length; i++) {
		int tmpFreq = counts.get(palavras[i]);
		if (tmpFreq > relFreq) {
			most = palavras[i];
			relFreq = tmpFreq;
		}
		total += tmpFreq;
	}	
	
	out.printf("MFW: %s\nRelative Freq.: %4.2f %%\n", most, 100 * (double)relFreq/(double)total);
  }
}

