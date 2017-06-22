import static java.lang.System.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import p2utils.*;

public class AllAnagrams{
	public static void main(String[] args) throws IOException{
		if (args.length != 1){
			 err.println("Uso: java -ea AllAnagrams <Ficheiro>");
			 exit(1);
		}
		long time = nanoTime();
		LinkedList<LinkedList<String>> anagrams = findAnagrams(args[0]);
		time = nanoTime() - time;
		out.println(anagrams);
		out.printf("Found %d anagram groups", anagrams.size());
		out.printf(" in %.3f seconds\n", time/1e9);
	}

	 // A sorted version of the string str:
	 //  	sortedString("isentas") -> "aeinsst".
	 //   sortedString("sinetas") -> "aeinsst".
	public static String sortedString(String str){
		char[] a = str.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}

	 // complete this function
	public static LinkedList<LinkedList<String>> findAnagrams(String FileName) throws IOException {
		File fin = new File(FileName);
		Scanner scin = new Scanner(fin);
		LinkedList<LinkedList<String>> anagrams = new LinkedList<>();
		int expectedNumWords = (int)(fin.length()/10.0); //assuming 10 bytes per word

		// tabela com anagramas e repetições
		HashTable<LinkedList<String>> reps = new HashTable<LinkedList<String>>(expectedNumWords);
		// um anagrama 
		LinkedList<String> anagrama;

		while(scin.hasNext()){
			// obtem a palavra
			String word = scin.next();
			// ordena a palavra
			String sortedWord = sortedString(word);
			// se a palavra ordenada já aconteceu, adiciona no fim da lista
			// caso contrário declara como "acontecida"
			if (reps.contains(sortedWord)) {
				anagrama = reps.get(sortedWord);
			} else {
				anagrama = new LinkedList<String>();
				reps.set(sortedWord, anagrama);
			}
			anagrama.addLast(word);
		}
		scin.close();

		// coloca as listas dos anagramas numa só lista
		String[] keys = reps.keys();
		for (int i = 0; i < keys.length; i++) {
			anagrama = reps.get(keys[i]);
			if (anagrama.size() > 1) {
				anagrams.addLast(anagrama);
			}
		}
		return anagrams;
	}
}