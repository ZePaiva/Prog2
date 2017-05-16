import static java.lang.System.*;
import p2utils.*;

public class E1001{
	public static void main(String[] args){
		if (args.length < 1){
			out.println("Usage: java -ea P101 <text>");
		} else {
			String palavra = concatenate(args);
			Queue fila = new Queue();
			Stack pilha = new Stack();

			// put the letters in a stack and a queue
			for	(int i = 0; i < palavra.length(); i++){
				fila.in(palavra.charAt(i));
				pilha.push(palavra.charAt(i));
			}
	
			boolean palindrome = true;
			
			// can switch between stack and queue size
			// makes no diference
			for (int i = 0; i < pilha.size(); i++){
				palindrome = (fila.peek() == pilha.top());
				pilha.pop();
				fila.out();
				if (!palindrome) {
					break;
				}
			}

			for	(int i = 0; i < args.length; i++){
				out.print(args[i] + " ");
			}
			out.printf("is%sa palindrome.\n", palindrome ? " " : " not ");
		}
	}

	// put all the words in one long word in lowercase
	public static String concatenate(String[] words){
		String pl = "";
		for	(int i = 0; i < words.length; i++) {
			pl += words[i];
		}
		return pl.toLowerCase();
	}
}
