import static java.lang.System.*;

public class P2{
	public static void main(String[] args){
		if (args.length == 0){
			out.println("uso: java -ea P2.java 1234");
		} else {
			for (int i = 0; i < args.length; i++){
				out.printf("%s -> %s\n", args[i],invertDigits(args[i]));
			}	
		}
	}

	public static String invertDigits(String inverter){
		assert inverter != null;
		if(inverter.length() == 1){
			return inverter;
		} else {
			return invertDigits(inverter.substring(1)) + inverter.charAt(0);
		}
	}
}

