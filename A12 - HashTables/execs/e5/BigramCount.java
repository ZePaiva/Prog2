import static java.lang.System.*;
import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.io.File;
import p2utils.HashTable;

public class BigramCount{

	public static void main(String[] args) {
		HashTable<Integer> counts = new HashTable<>(100000); // Usamos hash table!

		for (int a = 0; a < args.length; a++) { // Processa cada ficheiro
			File fin = new File(args[a]);
			analyseText(fin, counts); // atualiza contagens de bigramas
		}
		
		printCounts(counts);
		
		mostFrequent(counts);
	}

	// Read input text file word by word and count occurences of bigrams
	static void analyseText(File fin, HashTable<Integer> counts) {
		try {
			Scanner scf = new Scanner(fin);
			scf.useDelimiter("[^\\p{IsAlphabetic}]+");
			// ^ Isto serve para especificar que o separador de "tokens" no scanner
			// será qualquer sequência de 1 ou mais carateres não alfabéticos.
			// Assim, cada token devolvido por scf.next() é uma palavra no sentido
			// mais convencional: uma sequência composta apenas de letras!

			String prevWord = null; // serve para guardar a palavra anterior

			while (scf.hasNext()) { // Processa cada palavra
				// palavra atual: é lida do scanner e normalizada:
				String currWord = scf.next().toLowerCase();

				// Completar...
				if (prevWord != null){
					// variavel com o bigrama
					String bigram = prevWord + " " + currWord;
					// quantas vezes apareceu o bigrama 
					int bigramCount = 0;
					// se já apareceu obtem a chave para de pois a incrementar
					if (counts.contains(bigram)) {
						bigramCount = counts.get(bigram);
					}
					counts.set(bigram, bigramCount+1);
				} 
				prevWord = currWord;
			}
			scf.close();
		}
		catch (IOException e) {
			err.printf("ERROR: %s\n", e.getMessage());
			exit(1);
		}
	}

	// Print each key (bigram) and its count
	static void printCounts(HashTable<Integer> counts) {
		// Completar...
		// obtem os bigramas e ordena-os 
		String[] keys = counts.keys();
		Arrays.sort(keys);
		// imprime o par bigrama : contagem
		for(int i = 0; i < keys.length; ++i) {
				int keyVal = counts.get(keys[i]);
				System.out.printf("%s: %d\n", keys[i], keyVal);
		}
	}
		
	// Find and print the key with most occurrences
	// and its relative frequency.
	static void mostFrequent(HashTable<Integer> counts) {
		// Completar...
		int allBigramCount = 0;
		String biggestBigram = "";
		int biggestBigramCount = 0;
		String[] bigrams = counts.keys();

		for(int i = 0; i < bigrams.length; ++i) {
				int currentBigramCount = counts.get(bigrams[i]);
				if(currentBigramCount > biggestBigramCount) {
						biggestBigram = bigrams[i];
						biggestBigramCount = currentBigramCount;
				}

				allBigramCount += currentBigramCount;
		}

		System.out.printf("Number of keys: %d\n", counts.size());
		System.out.printf("Most frequent key: %s (%d/%d = %.2f%%)\n", biggestBigram, biggestBigramCount, allBigramCount, 100.0 * (double)biggestBigramCount / (double)allBigramCount);
	}
}