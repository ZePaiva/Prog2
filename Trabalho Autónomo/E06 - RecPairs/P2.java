import static java.lang.System.*;

public class P2{
	public static void main(String[] args){
		if (args.length == 0) {
			System.out.print("falta de argumentos");
		} else {
			for (int i = 0; i < args.length; i++){
				System.out.printf("%s contains %d pairs of consecutive equal characters\n", args[i], countPairs(args[i]));
			}
		}
	}

	public static int countPairs(String conta){
		if (conta.length() >= 2) {
			if (conta.charAt(0) == conta.charAt(1)) {
				return countPairs(conta.substring(1)) + 1;
			} else {
				return countPairs(conta.substring(1));
			}
		} else {
			return 0;
		}

		/*
		 * ou
		 * return ((conta.charAt(0) == conta.charAt(1) ? 1 : 0) + countPairs(conta.substring(1));
		 *
		 */
	}	
}