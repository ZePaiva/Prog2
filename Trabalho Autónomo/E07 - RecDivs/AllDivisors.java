import static java.lang.System.*;

public class AllDivisors{
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.print("Usage: java -ea AllDivisors <NUM>");
		} else {
			divers("", Integer.parseInt(args[0]));
		}
	}

	public static void divers(String tabs, int num){
		System.out.printf("%s%d\n", tabs, num);
		for (int i = num-1; i > 1; i--) {
			if (num % i == 0) {
				divers(tabs+"\t", i);
			}
		}
	}
}