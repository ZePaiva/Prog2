import static java.lang.System.*;
import java.util.Scanner;
import p2utils.*;

public class E1004{
	
	public static final Scanner k = new Scanner(in);

	public static void main(String[] args){
		Stack<Double> pile = new Stack();

		while(k.hasNext()){
			if(k.hasNextDouble()) {
				pile.push(k.nextDouble());
			} else {
				double rez = 0;
				String oper = k.next();
				
				if (!oper.equals("*") && !oper.equals("-") && !oper.equals("/") && !oper.equals("+")){
					out.println("operador não válido");
					exit(0);
				} 
					
				if (pile.isEmpty()){
					out.println("faltam 2 operandos");
					exit(0);
				}
				
				double op1 = pile.top();
				pile.pop();
				if (pile.isEmpty()){
					out.println("faltam operandos");
					exit(0);
				}
				
				double op2 = pile.top();
				pile.pop();
				if (oper.equals("*")){
					rez = op2 * op1;
				} else if (oper.equals("+")){
					rez = op2 + op1;
				} else if (oper.equals("-")){
					rez = op2 - op1;
				} else if (oper.equals("/")){
					if (op1 == 0){
						out.println("Divisão por zero não comportada");
						exit(0);
					}
					rez = op2 / op1;
				}
				pile.push(rez);
			}

			out.println("result: " + pile.toString());
		}
	}
}
