package e3;

public class Nota{
	private Data inicio;
	private Data fim;
	private String texto;

	public Data inicio(){
		return inicio;
	}

	public Data fim(){
		return fim;
	}

	public Nota(Data dia1, Data dia2, String texto){
		assert dia1.menorDoQue(dia2) : "o inicio não é antes do fim";
		assert texto != null;
		inicio = dia1;
		fim = dia2;
		this.texto = texto;
	}

	public void escreve(){
		inicio.escreve();
		System.out.print(" <-> ");
		fim.escreve();
		System.out.print(": ");
		System.out.println(texto);
	}
}