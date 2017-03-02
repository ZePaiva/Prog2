package e1;

public class Data{
	private static int dia;
	private static int mes;
	private static int ano;

	public Data(int d, int m, int a){
		dia = d;
		m = m;
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
		if (post.ano < this.ano) {
			return true;
		} else if (post.ano == this.ano) {
			if (post.mes < this.mes) {
				return true;
			} else if (post.mes == this.mes) {
				if (post.dia < this.mes) {
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
		if (post.ano > this.ano) {
			return true;
		} else if (post.ano == this.ano) {
			if (post.mes > this.mes) {
				return true;
			} else if (post.mes == this.mes) {
				if (post.dia > this.mes) {
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
		System.out.printf("%2d -%2d -%4d", dia, mes, ano);
	}
}
