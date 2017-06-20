import static java.lang.System.*;
import java.util.Scanner;
import java.io.*;
import p2utils.*;

public class PhoneCalls{
	public static void main(String[] args) {
		// contactos
		HashTable<String> contacts = new HashTable<String>(10);
		// cahamdas feitos por x
		HashTable<TimeNode> callsTo = new HashTable<TimeNode>(10);
		// chamadas recebidas por x
		HashTable<TimeNode> callsBy = new HashTable<TimeNode>(10);

		for (int i = 0; i < args.length; i++) {
			// trata de meter contacots
			if (args[i].endsWith(".nms")) {
				getContacts(new File(args[i]), contacts);
			} 
			// trata de meter chamadas feitas
			else if (args[i].endsWith(".cls")) {
				getCalls(new File(args[i]), contacts, callsTo, callsBy);
			}
			// resto dos argumentos se forem numeros
			else {
				System.out.print("\nChamadas feitas por ");
				if (contacts.contains(args[i])) {
					System.out.println(contacts.get(args[i]) + ":");
				} else {
					System.out.println(args[i] + ":");
				}

				TimeNode tmp;
				if (callsTo.contains(args[i])) {
					tmp = callsTo.get(args[i]);
					while (tmp != null){
						System.out.print("\t - para ");
						if (contacts.contains(tmp.number)) {
							System.out.print(contacts.get(tmp.number));
						} else {
							System.out.print(tmp.number + " ");
						}
						System.out.println("(" + tmp.time + " segundo" + (tmp.time == 1 ? "" : "s") + ")");
						tmp = tmp.next;
					}
				}

				System.out.print("\nChamadas recebidas por ");
				if (contacts.contains(args[i])) {
					System.out.println(contacts.get(args[i]) + ":");
				} else {
					System.out.println(args[i] + ":");
				}

				if (callsBy.contains(args[i])) {
					tmp = callsBy.get(args[i]);
					while (tmp != null) {
						System.out.print("\t - de ");
						if (contacts.contains(tmp.number)) {
							System.out.print(contacts.get(tmp.number));
						} else {
							System.out.print(tmp.number + " ");
						}
						System.out.println("(" + tmp.time + " segundo" + (tmp.time == 1 ? "" : "s") + ")");	
						tmp = tmp.next;
					}
				}

			}
		}
	}

	public static void getContacts(File fix, HashTable<String> contacts){
		try{
			Scanner kfix = new Scanner(fix);
			while(kfix.hasNextLine()){
				String[] line = ((kfix.nextLine()).trim()).split(" ");
				if (line.length < 2) {
					System.out.println("Ficheiro mal feito");
					exit(1);
				}
				String name = "";
				for (int i = 1; i < line.length; i++) {
					name += line[i] + " ";
				}

				if (!contacts.set(line[0], name)) {
					System.out.println("contacto já existente");
					exit(0);
				}
			}
			kfix.close();
		} catch (IOException e){
			System.out.println("Erro a ler o ficheiro");
			exit(0);
		}
	}

	public static void getCalls(File calls, HashTable<String> contacts, HashTable<TimeNode> callsTo, HashTable<TimeNode> callsBy){
		try{
			Scanner kfix = new Scanner(calls);
			while(kfix.hasNextLine()){
				String[] line = ((kfix.nextLine()).trim()).split(" ");
				assert line.length == 3 : "Ficheiro da chamadas mal feito";

				// numero que faz a chamada
				String numb1 = line[0];
				// numero que recebe a chamada
				String numb2 = line[1];
				// tempo
				int time = Integer.parseInt(line[2]);
				
				TimeNode callsFromThisNumber = new TimeNode(numb2, time);
				if (callsTo.contains(numb1)) {
					callsFromThisNumber.next = callsTo.get(numb1);
				}
				callsTo.set(numb1, callsFromThisNumber);

				TimeNode callsToThisNumber = new TimeNode(numb1, time);
				if (callsBy.contains(numb2)) {
					callsToThisNumber.next = callsBy.get(numb2);
				}
				callsBy.set(numb2, callsToThisNumber);

				if (contacts.contains(numb1)) {
					System.out.print(contacts.get(numb1) + "para ");
				} else {
					System.out.print(numb1 + " para ");
				}

				if (contacts.contains(numb2)) {
					System.out.print(contacts.get(numb2));
				} else {
					System.out.print(numb2 + " ");
				}

				System.out.println("(" + time + " segundo" + (time == 1 ? "" : "s") + ")");
			}
			kfix.close();
		} catch (IOException e) {
			System.out.println("erro ao ler o ficheiro");
			exit(0);
		} catch (NumberFormatException e){
			System.out.println("Erro no ficheiro");
			exit(0);
		}
	}

}


// nó com um número telefónico e um tempo que demorou
class TimeNode{
	String number;
	int time;
	TimeNode next = null;

	public TimeNode(String nb, int tm){
		number = nb;
		time = tm;
	}
}