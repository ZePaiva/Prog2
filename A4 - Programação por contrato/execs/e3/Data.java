package e3;

public class Data{
	private int dia;
	private int mes;
	private int ano;

	public Data(int d, int m, int a){
		assert tern(d, m, a) : "terno nao valido";
		dia = d;
		mes = m;
		ano = a;
	}

	public int dia(){
		return dia;
	}

	public int mes(){
		return mes;
	}

	public int ano(){
		return ano;
	}

	public boolean igualA(Data post){
		if (post.ano == this.ano && post.mes == this.mes && post.dia == this.dia) {
			return true;
		} else {
			return false;
		}
	}

	public boolean menorDoQue(Data post){
		if (post.ano > this.ano) {
			return true;
		} else if (post.ano == this.ano) {
			if (post.mes > this.mes) {
				return true;
			} else if (post.mes == this.mes) {
				if (post.dia > this.dia) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean maiorDoQue(Data post){
		if (post.ano < this.ano) {
			return true;
		} else if (post.ano == this.ano) {
			if (post.mes < this.mes) {
				return true;
			} else if (post.mes == this.mes) {
				if (post.dia < this.dia) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void escreve(){
		System.out.printf("%2d - %2d - %5d", dia, mes, ano);
	}

	public boolean tern(int dia, int mes, int ano){

		if (mes > 12 || mes < 1) {
			return false;
		} else {
			if (daysInMonth(mes, ano) >= dia && dia > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public int daysInMonth(int mes, int ano){

		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
			return 31;
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			return 30;
		} else if (mes == 2) {
			if (isBis(ano)) {
				return 29;
			} else {
				return 28;
			}
		} else {
			return 0;
		}
	}

	public boolean isBis(int ano){
		if ((ano % 4 == 0 && ano % 100 == 0) || ano % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
