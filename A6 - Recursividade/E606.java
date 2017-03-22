import java.util.*;

public class E606 {

	static Scanner k = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.print("Empréstimo: ");
		double loan = k.nextDouble();
		System.out.print("Taxa (%): ");
		double tax = k.nextDouble() / 100;
		System.out.print("Meses: ");
		int meses = k.nextInt();
		//double ln = iterLoan(loan, tax, meses);
		double ln = recLoan(loan,tax, meses);
		System.out.printf("Conta ao final de %d meses: %6.2f.\n", meses, ln);
	}

	public static double iterLoan(double req, double tax, int meses){

		assert meses > 0;
		double total = req + req * tax;
		
		for (int i = 1; i < meses; i++) {
			total += total * tax;
		}

		return total;
	}

	public static double recLoan(double req, double tax, int meses){

		if (meses == 1) {
			return (req + req * tax);
		} else if (meses == 0) {
			return req;
		} else {
			return recLoan((req + req * tax), tax, meses-1);
		}
	}
}