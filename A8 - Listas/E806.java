import java.util.Scanner;
import p2utils.*;
import E806.*;

public class E806{

	public static void main(String[] args) {
		
		SortedList<Pessoa> listaDePessoas = new SortedList<Pessoa>();

		listaDePessoas.insert(new Pessoa("joas", new Data(1, 1, 1)));
		listaDePessoas.insert(new Pessoa("jass", new Data(2, 1, 1)));
		listaDePessoas.insert(new Pessoa("joss", new Data(1, 2, 1)));
		listaDePessoas.insert(new Pessoa("jdss", new Data(1, 3, 1)));
		listaDePessoas.insert(new Pessoa("jocs", new Data(1, 1, 1)));

		listaDePessoas.print();
	}
}