import static java.lang.System.*;
import java.util.Scanner;

public class E1002{
	public static void main(String[] args){
		if(args.length!=1){
			out.println("Usage: java -ea P102 <n>");
		} else {
			Hanoi to = new Hanoi(Integer.parseInt(args[0]));
			to.solve();
			out.printf("Moves: %d\n", to.mvs());
		}
	}
}
