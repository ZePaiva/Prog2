import java.util.Scanner;

public class ExpressionTree{
	public static final Scanner KB = new Scanner(System.in);
	private ExpressionTree left = null;
	private ExpressionTree right = null;
	private String operador = null;
	private double number;
	private boolean valido = true;
	private String[] validOpers = {"+", "-", "*", "/"};

	// inicializa a arvore, mas de duas maneiras diferentes
	public ExpressionTree(String fix){
		// se for notação prefixa
		if (fix.equals("PRE")) {
			if (!KB.hasNext()) {
				valido = false;
			} else if (KB.hasNextDouble()) {
				number = KB.nextDouble();
			} else {
				operador = KB.next();
				left = new ExpressionTree(fix);
				right = new ExpressionTree(fix);
				valido = (validOperator(operador) && left.valido && right.valido);
			}
		}

		// se for notação sufixa
		else if (fix.equals("SU")) {
			if (!KB.hasNextDouble()) {
				valido = false;
			} else {
				number = KB.nextDouble();

				while (KB.hasNext()) {
					do{
						if (!valido || !KB.hasNextDouble()) {
							return;
						}

						left = new ExpressionTree(this);
						right = new ExpressionTree(fix);
						valido = KB.hasNext();
					} while(!valido);

					operador = KB.next();
					valido = (validOperator(operador) && left.valido && right.valido);
				}
			}
		}	else {
			System.out.print("ya not");
			System.exit(2);
		}
	}

	// segunda maneira, pq sufix
	public ExpressionTree(ExpressionTree et){
		left = et.left;
		right = et.right;
		operador = et.operador;
		number = et.number;
		valido = et.valido;
	}

	public boolean valid(){
		return valido;
	}

	// imprime a expressão na notação infixa
	public void printInfix(){
		if (operador == null) {
			System.out.print(number);
		} else {
			System.out.print("(");
			left.printInfix();
			System.out.print(operador);
			right.printInfix();
			System.out.print(")");
		}
	}

	// calculadora
	public double eval(){
		if (operador == null) {
			return number;
		} else {
			if (operador.equals("*")) {
				return (left.eval() * right.eval()); 	
			} else if (operador.equals("+")) {
				return (left.eval() + right.eval());
			} else if (operador.equals("-")) {
				return (left.eval() - right.eval());
			} else if (operador.equals("/")) {
				return (left.eval() / right.eval());
			} else {
				return 0.0;
			}
		}

	}


	// verfica se o operador escolhido é válido
	public boolean validOperator(String oper){
		for (int i = 0; i < validOpers.length; i++) {
			if (validOpers[i].equals(oper)) {
				return true;
			}
		}
		return false;
	}

	
} 