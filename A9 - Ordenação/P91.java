import static java.lang.System.*;
import java.util.Scanner;

public class P91 {

	public static void main(String[] args) {
		NumberArray na = new NumberArray();
		mergeSort(na.array(), 0, na.length());
		na.print();
	}

	// merge sort
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
	}
}
