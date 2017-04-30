import static java.lang.System.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class P94 {

	public static void main(String[] args) throws IOException /* Note that the Program is not robust!*/{
		if (args.length < 1) {
  	  		err.println("Usage: java P94 <file>");
  	  		exit(1);
		}
		
		// read the words from each file
		String[][] wordsi = new String[args.length][];
		for (int i = 0; i < args.length; i++){
			File fin = new File(args[i]);
			wordsi[i] = extractsWords(fin);
		}

		// all arrays in one
		String[] words = allInOne(wordsi);

		// if we sort before removal it's easier to take out the repeated words
		mergeSort(words, 0, words.length);
		words = dimList(words);
		for(int i = 0; i < words.length; i++){
  	  		out.printf("[%05d] %s\n", i+1, words[i]);
		}
	}

	// Read words from a file, return words in an array
	public static String[] extractsWords(File fin) throws IOException {
		assert fin != null;
		assert !fin.isDirectory(); // Note that this precondition does not 100% ensure this kind of assertion!
		assert fin.canRead();      // Note that this precondition does not 100% ensure this kind of assertion!

		// count the words
		int n = 0;
		Scanner scf = new Scanner(fin).useDelimiter("[\\p{Punct}\\p{Space}]+");
		// (words are delimited by 1 or more punctuation or whitespace char)
		while (scf.hasNext()) {
  	  		scf.next();
  	  		n++;
		}
		scf.close();

		String[] result = new String[n];

		// read the words
		int i = 0;
		scf = new Scanner(fin).useDelimiter("[\\p{Punct}\\p{Space}]+");
		while(scf.hasNext()) {
  	  		result[i] = scf.next();
  	  		i++;
		}
		scf.close();

		return result;
	}

	// all arrays of words to one
	public static String[] allInOne(String[][] words){
		// new array length
		int newLength = 0;
		for	(int i = 0; i < words.length; i++){
			newLength += words[i].length;
		}
		
		// new array
		String[] newArray = new String[newLength];
		
		// put the arrays each in the new main array
		int len = 0;
		for (int i = 0; i < words.length; i++){
			arraycopy(words[i], 0, newArray, len, words[i].length);
			len += words[i].length;
		}
		
		return newArray;
	}


	// concatenate the list so words do not repeat
	public static String[] dimList(String[] ar){
		// count the amount of different words
		int difs = 1;
		for	(int i = 1; i < ar.length; i++){
			if (!ar[i].equals(ar[i-1])) {
				difs++;
			}
		}
		
		//create a new array for the words
		String[] words = new String[difs];
		int n = 1;
		words[0] = ar[0];
		for (int i = 1; i < ar.length; i++){
			if(!ar[i].equals(ar[i-1])){
				words[n++] = ar[i];
			}		}
		return words;
	}
	
	// merge sort 
	public static void mergeSort(String[] a, int start, int end) {
		if (end - start > 1) {
			int tmp = (end + start) / 2;  
			// apply mergeSort to the first half of the array
			mergeSort(a, start, tmp);
			// apply mergeSort to the second half of the array
			mergeSort(a, tmp, end);
			// apply sorting in the merge of the subarrays
			mergeSubs(a, start, tmp, end);
		}
	}
	
	// merge each subarray
	public static void mergeSubs(String[] ar, int start, int middle, int stop){
		// temporary array to store
		String tmp[] = new String[stop - start];
		// beginning of the array
		int bot = start;
		// end of the array
		int top = middle;
		// current position in temporary array
		int arrayIndex = 0;

		// main sorting
		// do ++ instead of second line to make var+=1
		while(bot < middle && top < stop){
			if (ar[bot].compareTo(ar[top]) < 0){
				tmp[arrayIndex++] = ar[bot++];
			} else {
				tmp[arrayIndex++] = ar[top++];
			}
		}
		
		// secondary sortings
		while(bot < middle){
			tmp[arrayIndex++] = ar[bot++];
		}
		while(top < stop){
			tmp[arrayIndex++] = ar[top++];
		}
		
		// copy the temporary array to the main array
		arraycopy(tmp, 0, ar, start, stop - start);
	}
}
