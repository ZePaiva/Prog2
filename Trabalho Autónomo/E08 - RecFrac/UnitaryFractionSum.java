import static java.lang.System.*;

public class UnitaryFractionSum{
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.print("Usage: java -ea UnitaryFractionSum <NUM> <DEN>");
		} else {
			int num = Integer.parseInt(args[0]);
			int den = Integer.parseInt(args[1]);
			System.out.printf("%d/%d = %s\n", num, den, fractions(num, den));
		}
	}

	public static String fractions(int num, int den){
		if (den % num == 0) {
			return "1/" + (den/num);
		} else {
			return "1/" + (den/num + 1) + " + " + fractions(num * (den/num + 1) - den, den * (den/num + 1));
		}
	} 
}