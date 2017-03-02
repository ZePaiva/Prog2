package e3;

import e1.*;

public class Nota{
	private static Data inicio;
	private static Data fim;
	private static String texto;

	public static Data inicio(){
		return inicio;
	}

	public static Data fim(){
		return fim;
	}

	public Nota(Data dia1, Data dia2, String texto){
		inicio = dia1;
		fim = dia2;
		this.texto = texto;
	}

	public void escreve(){
		System.out.println(texto);
	}
}