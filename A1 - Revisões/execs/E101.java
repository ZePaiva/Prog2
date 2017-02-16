import java.util.*;
import static java.lang.System.*;

public class E101{
	static final Scanner k = new Scanner(in);
	public static void main(String[] args) {
		
		System.out.print("Operação: ");
		String sl = k.nextLine();

		String[] lls = new String[3];
		lls = sl.split(" ");
		
		char oper = lls[1].charAt(0);
		double num1 = Double.parseDouble(lls[0]);
		double num2 = Double.parseDouble(lls[2]);
		double rez;

		switch (oper) {
			case '+':
				rez = num1 + num2;
				System.out.println(rez);
				break;
			case '-':
				rez = num1 - num2;
				System.out.println(rez);
				break;
			case '*':
				rez = num1 * num2;
				System.out.println(rez);
				break;
			case '/':
				rez = num1 / num2;
				System.out.println(rez);
				break;

			default:
				System.out.println("Operação não possível.");
				break;
		}
	}
}