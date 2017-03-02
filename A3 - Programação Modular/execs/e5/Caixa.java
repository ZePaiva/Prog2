package e5;
import static java.lang.System.*;

public class Caixa{
	
	//array com das moedas
	private long[] moedas = new long[10];
	//numero de moedas atual
	private int nMod = 0;

	//metodo para adicionar moedas
	public void adicionaMoeda(long moeda){
		
		//se está no limite do array 
		//cria outro e copia
		//espécie de método "lista"
		if (moedas.length  == nMod) {			
			long[] mods = new long[moedas.length +1];
			arraycopy(moedas, 0,mods, 0, moedas.length);
			moedas = mods;
		}

		//adiciona
		moedas[nMod] = moeda;
		nMod++;
		bubbleSort();
	}

	//bubblesort
	private void bubbleSort(){

		boolean swap = false;
		long temp;
		
		do{
			swap = false;

			for (int i = 0; i < moedas.length-1; i++) {
				if (moedas[i] < moedas[i+1]) {
					temp = moedas[i];
					moedas[i] = moedas[i+1];
					moedas[i+1] = temp;
					swap = true;
				}
			}
		}while(swap);
	}

	//metodo para retirar as moedas
	public long[] retiraDinheiro(long valorMin){

		int moedasAretirar = 0;
		long valRet = 0;
		long[] posRetiro;

		for (int i = moedas.length-1; i >= 0; i--) {
			if ((valRet + moedas[i]) < valorMin) {
				valRet += moedas[i];
				moedasAretirar++;		
			} else if ((valRet + moedas[i]) == valorMin) {
				valRet += moedas[i];
				moedasAretirar++;
				break;
			}
		}

		if ((nMod - moedasAretirar) <= 0) {
			posRetiro = new long[10];
		} else {
			posRetiro = new long[nMod - moedasAretirar];
			moedasAretirar = 0;	
		}
		
		for (int i = moedas.length-1; i >= 0; i--) {
			if ((valRet + moedas[i]) < valorMin) {
				valRet += moedas[i];		
			} else if ((valRet + moedas[i]) == valorMin) {
				valRet += moedas[i];
				break;
			} else {
				posRetiro[moedasAretirar] = moedas[i];
				moedasAretirar++;
			}
		}

		arraycopy(posRetiro, 0, moedas, 0, posRetiro.length);
		return posRetiro;
	}

	//metodo para ver as moedas existentes
	public long[] moedas(){
		long[] moeds = new long[nMod];
		moeds = moedas;
		return moeds;
	}

	//metodo para ver o total
	public long total(){
		long total = 0;

		for (int i = 0; i < nMod; i++) {
			total += moedas[i];
		}

		return total;
	}

	public boolean daTroco(long minVal){

		long val = 0;
		boolean da = false;

		for (int i = moedas.length-1; i >= 0; i--) {
			if ((val + moedas[i]) < minVal) {
				val += moedas[i];
			} else if ((val + moedas[i]) == minVal) {
				da = true;
				break;
			} else if ((val + moedas[i]) > minVal) {
				da = false;
			}
		}

		return da;
	}
}