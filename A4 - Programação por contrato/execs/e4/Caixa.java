package e4;
import static java.lang.System.*;

public class Caixa{
	
	//array com das moedas
	private long[] moedas = new long[10];
	//numero de moedas atual
	private int nMod = 0;

	//metodo para validar a moeda
	public static boolean moedaValida(long moeda) {
        boolean valeQuanto = moeda > 0;

        long valor;
        for(valor = moeda; valeQuanto && valor >= 10; valor /= 10) {
            valeQuanto = valor % 10 == 0;
        }

        valeQuanto = valeQuanto && (valor == 1 || valor == 2 || valor == 5);
        return valeQuanto;
    }

	//metodo para adicionar moedas
	public void adicionaMoeda(long moeda){
		
		assert moedaValida(moeda) : "moeda não válida";

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

		assert daTroco(valorMin) : "não é possível dar troco";
		assert valorMin < 0L : "pede negativos";

		long valInMod = 0;

        int mds;
        for(mds = 0; valInMod < valorMin; ++mds) {
            valInMod += moedas[mds];
        }

        long[] moedasPosRetiro = new long[mds];
        valInMod = 0;

        for(mds = 0; valInMod < valorMin; ++mds) {
            moedasPosRetiro[mds] = moedas[mds];
            valInMod += moedas[mds];
        }

        System.arraycopy(moedas, mds, moedas, 0, nMod - mds);
        nMod -= mds;
        return moedasPosRetiro;
	}

	//metodo para ver as moedas existentes
	public long[] moedas(){
		return moedas;
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