import static java.lang.System.*;
import java.util.*;

//feito com base no esqueleto de p21

public class E201 {

	static final Scanner k = new Scanner(in);

	public static void main(String[] args) {
		
		if (args.length != 0 && args.length != 2) {	
			System.exit(1);
		}

		Complexo a;
		
		if (args.length == 2) {

			a = new Complexo(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
		
		} else {
			
			System.out.print("Re: ");
			double re = k.nextDouble();
			System.out.print("Im: ");
			double im = k.nextDouble();

			a = new Complexo(re, im);
		}

		// Vamos usar métodos de 'a'
		out.println("(" + a.real() + " + " + a.imag() + "i)");
		out.println("  parte real = " + a.real());
		out.println("  parte imaginaria = " + a.imag());
		out.println("  modulo = " + a.abs());
		out.printf("  fase   =  %2.2f\n", a.phase());
	}
}