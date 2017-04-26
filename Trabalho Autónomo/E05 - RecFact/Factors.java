import static java.lang.System.*;

public class Factors{
	public static void main(String[] args){
		for(int i = 0; i < args.length; i++){
			out.println(args[i]+" = "+factors(Integer.parseInt(args[i])));
		}
	}

	public static int factors(int i){
		if (i <= 1) {
			return 1;
		} else {
			return i * factors(i-1);
		}
	}
}