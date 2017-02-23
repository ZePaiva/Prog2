import java.util.*;
import static java.lang.System.*;

public class E105{

	static final Scanner k = new Scanner(in);

	public static void main(String[] args) {
		
		double avg = 0;
		double sum = 0;
		int len = 0;
		double num;

		while (true){

			num = k.nextDouble();
			if (num == 0) {
				break;
			} else{
				sum += num;
				len++;
			}
		}

		if (len == 0) {
			System.out.print("Media: Nan\nSoma: 0\n");
		} else {
			avg = sum/len;
			System.out.printf("Media: %4.2f\nSoma: %4.2f\n", avg, sum);
		}
	}
}