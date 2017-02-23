import java.util.*;

public class Data{
	
	private static int dia;
	private static int mes;
	private static int ano;	

	public void printData(){
		System.out.printf("%2d-%2d-%4d\n", dia, mes, ano);
	} 

	public static boolean isBis(){
		if ((ano % 4 == 0 && ano % 100 == 0) || ano % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int daysInMonth(){

		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
			return 31;
		} else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			return 30;
		} else if (mes == 2) {
			if (isBis()) {
				return 29;
			} else {
				return 28;
			}
		} else {
			return 0;
		}
	}

	public static boolean tern(){

		if (mes > 12 || mes < 1) {
			return false;
		} else {
			if (daysInMonth() > dia && dia > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Data(){
		Calendar hj = Calendar.getInstance();
		ano = hj.get(Calendar.YEAR);
		mes = hj.get(Calendar.MONTH);
		dia = hj.get(Calendar.DAY_OF_MONTH);
	}

	public Data(int dia, int mes, int ano){
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
	}

	public int ano(){
		return ano;
	}

	public int mes(){
		return mes;
	}

	public int dia(){
		return dia;
	}

	public String extenseMes(){
		if (mes == 1) {
			return "Janeiro";
		} else if (mes == 2) {
			return "Fevereiro";
		} else if (mes == 3) {
			return "Março";
		} else if (mes == 4) {
			return "Abril";
		} else if (mes == 5) {
			return "Maio";
		} else if (mes == 6) {
			return "Junho";
		} else if (mes == 7) {
			return "Julho";
		} else if (mes == 8) {
			return "Agosto";
		} else if (mes == 9) {
			return "Setembro";
		} else if (mes == 10) {
			return "Outubro";
		} else if (mes == 11) {
			return "Novembro";
		} else if (mes == 12) {
			return "Dezembro";
		} else {
			return "Mês não válido";
		}
	}

	public void extenseDate(){
		System.out.printf("%2d de %s de %4d\n", dia, extenseMes(), ano);
	}

	public void vaiParaAmanha(){
		dia++;
		if (dia > daysInMonth()) {
			dia = 1;
			mes++;

			if (mes > 12) {
				ano++;
				mes = 1;
				dia = 1;
			}
		}
	}

	public void vaiParaOntem(){
		dia--;
		if (dia == 0) {
			mes--;
			dia = daysInMonth();

			if (mes == 0) {
				ano--;
				mes = 12;
				dia = 31;
			}
		}
	}
}