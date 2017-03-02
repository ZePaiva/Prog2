package e4;

import e1.*;
import e3.*;
import static java.lang.System.*;

public class Agenda{
	private static Nota[] tars= new Nota[0];

	public void novaNota(Nota t1){
		Nota[] tarefs = new Nota[this.tars.length + 1];
		arraycopy(this.tars, 0, tarefs, 0, this.tars.length);
		tarefs[tars.length] = t1;
	        tars = tarefs;
	}

	public Nota[] compromissos(Data d1, Data d2){
		int comprs = 0;

		for (int i = 0; i < tars.length; i++) {
			if (d2.maiorDoQue(tars[i].inicio()) && d1.menorDoQue(tars[i].fim())) {
				comprs++;
			}
		}

		Nota[] compromissiva = new Nota[comprs];
		int lenDoArray = 0;

		for (int i = 0; i < tars.length; i++) {
			if (d2.maiorDoQue(tars[i].inicio()) && d1.menorDoQue(tars[i].fim())) {
				compromissiva[lenDoArray++] = tars[i];
			}	
		}

		return compromissiva;
	}

	public void escreve(){
		System.out.println("Agenda:");

		for (int i = 0; i < tars.length; i++) {
			(tars[i].inicio()).escreve();
			System.out.print(" <-> ");
			(tars[i].fim()).escreve();
			tars[i].escreve();
		}
	}
}
