public class E604 {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Precisa de arumentos");
			System.exit(1);
		} else {
			for (int i = 0; i < args.length; i++) {
				System.out.println(invertString(args[i], args[i].length()));
			}
		}
	}

	public static String invertString(String palavra, int len){
		if (len >= 2){
			String rez = palavra.charAt(len-1) + invertString(palavra, len-1);
			return rez;
		} else {
			return ("" + palavra.charAt(0));
		}
	}
}