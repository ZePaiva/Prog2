import static java.lang.System.*;
import java.util.Scanner;

public class P132{
	public static void main(String[] args){
	
		// notação sufixa
		// descomentar pra testar
		/*
		System.out.println("To finish expresion press Enter-> Ctrl+D");
		ExpressionTree arvore = new ExpressionTree("SU");
		if (!arvore.valid()) {
			System.out.println("expressão não aceitável");
			exit(0);
		}
		arvore.printInfix();
		System.out.println(" = " + arvore.eval());
	  */

	  // notação prefixa
		ExpressionTree arvore = new ExpressionTree("PRE");
		if (!arvore.valid()) {
			System.out.println("expressão não aceitável");
			exit(0);
		}
		arvore.printInfix();
		System.out.println(" = " + arvore.eval());
		
	}
}