import static java.lang.System.*;
import java.util.Arrays;    // to use Java's array operations

public class P93{
	
	// Fields to measure times:
	public static long startTime;  // start time of latest measurement (in nanoseconds)
	public static double elapsedTime;  // seconds elapsed in latest measurement

	// Fields to count operations: 
	public static long assignCount = 0L;
	public static long comparisonCount = 0L;


	public static void main(String[] args){
		if (args.length != 1){
			err.println("Test sorting algorithms for a N number array.");
			err.println("Usage: java -ea P93 <N>");
			exit(1);
		}

		int n = Integer.valueOf(args[0]);
		// Create 3 arrays, each with n numbers, in 3 different orders:
		int[][] arrays = new int[3][];
		arrays[0] = randomArray(n, n/2);    // random numbers, random order
		arrays[1] = arrays[0].clone();
		Arrays.sort(arrays[1]);             // same numbers, in increasing order
		arrays[2] = reverseOf(arrays[1]);   // same numbers, in decreasing order

		// Labels for these arrays:
		String[] arrayType = {"random", "increasing", "decreasing"};

		// Labels for algorithms to test:
		String[] algorithm = {"Sequential", "Bubble", "Insertion", "Merge"};

		out.printf("%-10s | %-10s | %8s | %12s | %12s | %10s | %-4s\n", "Algorithm", "Array", "Size", "Assignments", "Comparisons", "Time (s)", "OK?");
		for(int i = 0; i < algorithm.length; i++) {
			out.printf("-----------|------------|----------|--------------|--------------|------------|------\n");
			for(int j = 0; j < 3; j++) {
				int[] aCopy = arrays[j].clone();  // new int[n]; arraycopy(arrays[j], 0, aCopy, 0, n);

				startMeasuring();

				if(i == 0){
  	  	  	  	  sequentialSort(aCopy, 0, aCopy.length);
				}else if(i == 1){
  	  	  	  	  bubbleSort(aCopy, 0, aCopy.length);
				}else if(i == 2){
  	  	  	  	  insertionSort(aCopy, 0, aCopy.length);
				}else if(i == 3){
  	  	  	  	  mergeSort(aCopy, 0, aCopy.length);
				}
				stopMeasuring();

				// Check if sorting worked (by comparing with Java's sort result):
				String worked = Arrays.equals(aCopy, arrays[1])? "OK" : "FAIL";
				out.printf("%-10s | %-10s | %8d | %12d | %12d | %10.6f | %-4s\n", algorithm[i], arrayType[j], n, assignCount, comparisonCount, elapsedTime, worked);
			}
		}
	}

	// Start a new measurement
	public static void startMeasuring() {
		assignCount = 0L;
		comparisonCount = 0L;
		startTime = nanoTime();
	}

	// Stop a measurement (determine elapsedTime)
	public static void stopMeasuring() {
		elapsedTime = (double)(nanoTime() - startTime) * 1e-9;
	}

	/**
	* Creates an array filled with random integers in the interval [0; max].
	* @param len length of the array.
	* @param max maximum random value.
	* @return the array
	*/
	public static int[] randomArray(int len, int max) {
		assert len >= 0;
		assert max >= 0;

		int[] result = new int[len];
		for (int i = 0; i < len; i++){
			result[i] = (int)(Math.random()*(max+1));
		}
		return result;
	}

	/** Reverse copy of array.
	* @param a original array
	* @return an array with the elements of a, in reverse
	*/
	public static int[] reverseOf(int[] a) {
		int len = a.length;
		int[] result = new int[len];
		for (int i = 0; i < len; i++){
			result[i] = a[len-1-i];
		}
		return result;
	}

	/**
	* Swaps two elements of an integer array.
	* @param a the array
	* @param i index of an element to swap
	* @param j index of the other element to swap
	* {@code i},{@code j} must be valid indexes within array {@code a}
	*/
	public static void swap(int[] a, int i, int j) {
		assert a!=null;
		assert 0<=i && i<a.length;
		assert 0<=j && j<a.length;

		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		// muh assignments
		// temp = 1
		// a[i] = 2
		// a[j] = 3
		assignCount+=3;
	}

	/**
	* Sequential sort ("greedy" variation of selection sort).
	* Increasing sorting of integer subarray a[start..end[
	* @param a      the array to be (partially) sorted.
	* @param start  index of the first element to sort.
	* @param end    index of the first element not to be sorted (the last element to sort is {@code end-1}).
	* Requires:   a!=null and 0 <= start <= end <= a.length
	* Ensures:  The elements a[k] with start <= k < end are sorted.  the remaining elements are not changed.
	*/
	public static void sequentialSort(int[] a, int start, int end) {
		assert a!=null;
		assert 0<=start && start<=end && end<=a.length;

		for (int i=start; i<end-1; i++) { // For each element (except the last):
			for (int j=i+1; j<end; j++) {   // Scan every following element
				if (a[j] < a[i]) {            // compare them
  	  	  	  	  swap(a, i, j);              // if necessary, swap them
				}
				comparisonCount++;
			}
		}
	}

	/**
	* Bubble sort.
	*/
	public static void bubbleSort(int[] a, int start, int end) {
		assert a!=null;
		assert 0<=start && start<=end && end<=a.length;

		int f = end;
		boolean existsSwap;
		do {
			existsSwap = false;
			for(int i=start; i<f-1; i++) {
				comparisonCount++;
				if (a[i] > a[i+1]) {
  	  	  			swap(a, i, i+1);
  	  	  			existsSwap = true;
				}
			}
			f--;
		} while (f>start+1 && existsSwap);
	}

	/** Ordenação por inserção, (variante com inserção sequencial). */
	public static void insertionSort(int[] a, int start, int end) {	
        // muh cycle
		for(int i = start; i < end; ++i) {
            // so shitstorm doesn't happen
			int tmp = a[i];
            // gonna need that boiii
			int j;
			// muh sorting
            for(j = i - 1; j >= start && a[j] > tmp; --j) {
                // muh increases inside
				comparisonCount++;
				assignCount++;
				a[j + 1] = a[j];
            }
			// muh insertion
            a[j + 1] = tmp;
			// muh increases
			comparisonCount++;
			assignCount += 2;
        }
	}

	/** Merge sort */
	public static void mergeSort(int[] a, int start, int end) {
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
	public static void mergeSubs(int[] ar, int start, int middle, int stop){
		// temporary array to store
		int tmp[] = new int[stop - start];
		// beginning of the array
		int bot = start;
		// end of the array
		int top = middle;
		// current position in temporary array
		int arrayIndex = 0;

		// main sorting
		// do ++ instead of second line to make var+=1
		while(bot < middle && top < stop){
			comparisonCount++;
			if (ar[bot] < ar[top]){
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
		assignCount += (stop - start);
	}
}
