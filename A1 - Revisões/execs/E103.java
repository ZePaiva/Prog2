import static java.lang.System.*;
import java.util.Scanner;

// baseado no esquele p13.java

public class E103{
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args){
		int n;
		do{
			out.print("N: ");
			n = in.nextInt();
			if(n < 1){
				err.println("ERROR: invalid number!");
			}
		}while(n < 1);

		String verb = isPrime(n)? "is" : "is not";
		out.printf("Number %d %s prime\n", n, verb);
	}

	public static boolean isPrime(int n){
		if (n < 2) {
			return false;
		} else if (n == 2) {
			return true;
		} else {
			boolean flag = true;
			for (int i = 2; i < n; i++) {
				if (n % i == 0) {
					flag = false;
					break;
				}
			}
			return flag;
		}
	}
}