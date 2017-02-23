import pt.ua.prog2.Contacto;
import java.util.*;

public class E202{

	static final Scanner k = new Scanner(System.in);

	public static void main(String[] args){
		Contacto[] cl = new Contacto[4];
		
		for (int i = 0; i < 4; i++) {
			System.out.print("Nome: ");
			String nm = k.nextLine();	
			System.out.print("Telemovel: ");
			String tel = k.nextLine();
			System.out.print("Email:");
			String mail = k.nextLine();

			if (nm == "" && tel == "" && mail == "") {
				System.out.print("Contacto Inválido!");
				System.exit(1);
			} else {
				cl[i] = new Contacto(nm, tel, mail);
			}
		}

		for (int i = 0; i < cl.length; i++){
			cl[i].printCont();
		}

		System.out.printf("Contactos: %d\n", cl[3].contactos);
	}
}
