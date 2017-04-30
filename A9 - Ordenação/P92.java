import static java.lang.System.*;
import java.util.Scanner;

public class P92 {

	public static void main(String[] args) {
		NumberArray na = new NumberArray();
		insertionSort(na.array(), 0, na.length());
		na.print();
	}
	
	public static void insertionSort(int[] a, int start, int end) {
        // muh cycle
		for(int i = start; i < end; ++i) {
            // so shitstorm doesn't happen
			int tmp = a[i];
            // gonna need that boiii
			int j;
			// muh sorting
            for(j = i - 1; j >= start && a[j] > tmp; --j) {
                a[j + 1] = a[j];
            }
			// muh insertion
            a[j + 1] = tmp;
        }
    }
}
