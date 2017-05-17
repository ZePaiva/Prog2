import p2utils.*;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.*;

import static java.lang.System.*;

public class E1101{
	static final Scanner kb = new Scanner(in);

	public static void main(String[] args){
		KeyValueList dict = new KeyValueList();
		
		if (args.length != 1) {
			out.println("Usage: java -ea P111 <password file>");
			exit(1);
		}

		File fix = new File(args[0]);
		
		// read the database
		// if anything fails there is no problem		
		try {
			Scanner kfile = new Scanner(fix);
			String[] logs;
			
			while (kfile.hasNextLine()){
				logs = kfile.nextLine().split(" ");
				dict.set(logs[0], logs[1]);
			}
		} catch (Exception e){
			out.print("Password database could not be read.\nHave a nice day.\n");
		}
		
		boolean stop = true;
		do {
			try {			

				out.print("Login:    ");
				String name = kb.nextLine();
				out.print("Password: ");	
				String pwd = kb.nextLine();
				if (!dict.contains(name)) {
					out.println("No account for that.");
				} else if (dict.get(name).equals(pwd)) {
					out.println("U GOT IN\nSUCH HACKER\nMUCH WOW");
				} else {
					out.println("U SUCK");
				}
			} catch (NoSuchElementException e){
				out.println();
				stop = false;
			}
		} while (stop);
	}
}
