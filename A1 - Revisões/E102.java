import static java.lang.System.*;
import java.util.Scanner;

// Complete o programa para calcular a nota final NF de
// um aluno de Programação 2 (2016--2017) na época normal.
// baseado no esqueleto p12.java
public class E102{
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args){
		double tpi1 = readInRange("TPI1: ", 0.0, 20.0);
		double tpi2 = readInRange("TPI2: ", 0.0, 20.0);
		double pg1 = readInRange("PG1: ", 0.0, 20.0);
		double pg2 = readInRange("PG2: ", 0.0, 20.0);
		double pg3 = readInRange("PG3: ", 0.0, 20.0);
		double ef = readInRange("EF: ", 0.0, 20.0);
		double pg;
		double tp, p, nf;

		tp = (tpi1 + tpi2) / 2;
		pg = (pg1 + pg2 + pg3) / 3;

		if (pg > ef + 4) {
			p = 0.75 * ef + 0.25 * pg - 1;
		} else if (pg < ef - 4) {
			p = 0.75 * ef + 0.25 * pg + 1;
		} else {
			p = 0.5 * pg + 0.5 * ef;
		}

		nf = p * 0.8 + tp * 0.2;

		out.printf("PG = %.1f\n", pg);
		out.printf("TP = %.1f\n", tp);
		out.printf("P  = %.1f\n", p);
		out.printf("NF = %.1f\n", nf);
		if (nf < 9.5){
			out.println("REPROVADO!");
		}else{
			out.println("APROVADO!");
		}
	}

	// Reads a double that must be in the range [min, max].
	// Shows the prompt, reads a double value.
	// If the value is not in the range, prints a warning and repeats.
	public static double readInRange(String prompt, double min, double max){
		assert min <= max : "max must be at least as large as min!";
		double value;
		do{
			System.out.print(prompt);
			value = in.nextDouble();
		}while(value < min || value > max);
		return value;
	}
}