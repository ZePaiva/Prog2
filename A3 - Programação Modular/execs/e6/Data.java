package e6;

import java.util.*;

public class Data{

	private long numComRef; //referencia da data como pedido no inicio do exec
	private long ref=730486; //toDias(2000, 1, 1) (0-00-0000 a 1-01-2000) / termo de comparação com a data jliana

	public void dataPrint(){
		System.out.printf("%02d-%02d-%04d",converter(2, ref+numComRef),converter(1, ref+numComRef),converter(0, ref+numComRef)); 
	}

	//cria a data com o calendário
	public Data(){
		Calendar hoje = Calendar.getInstance();
		numComRef = toDias(hoje.get(Calendar.YEAR), hoje.get(Calendar.MONTH)+1, hoje.get(Calendar.DAY_OF_MONTH))-ref;
	}
	
	//cria a data com os dias 
	public Data(long refer){
		numComRef = refer;
	}
	
	//cria a data com as ordenações
	public Data(int a, int b, int c){
		numComRef = toDias(a, b, c)-ref;
	}
	
	//converte para os dias de diferença
	public long toDias(int ano, int mes, int dia){
		long dias=0;

		for(int a=0; a<ano; a++){
			dias+=diasAno(a);
		}

		for(int m=1; m<mes; m++){
			dias+=nDias(ano, m);
		}

		dias+=dia;
		return dias;
	}
	
	//obvio
	private int diasAno(int a){
		if(bisSexto(a)){
			return 366;
		} else{
			return 365;
		}
	}

	//oh plz
	public int converter(int opt, long g){ //0=ano, 1=mes, 2=dia
		int ano=0, mes=1;

		for(int a=0; g>(long)diasAno(a); a++){
			g-=diasAno(a);
			ano++;
		}

		for(int m=1; g>nDias(ano, m); m++){
			g-=nDias(ano, m);
			mes++;
			if(mes>=12){
				break;
			}
		}

		int dias=(int)g;
		
		if(opt==0){
			return ano;
		} else if(opt==1){
			return mes;
		}else{ 
			return dias;
		}
	} 
	
	//ta no nome
	public String dataExtenso(){
		return converter(2, ref + numComRef) + " de " + nomeMes(converter(1, ref + numComRef)) + " de " + converter(0, ref + numComRef);
	}  
	
	//ta no nome
	public boolean igual(Data a){
		if(this.numComRef==a.numComRef){
			return true;
		}else{
			return false;
		}
	}

	//ta no nome
	public boolean maior(Data a){
		if(this.numComRef<a.numComRef){
			return true;
		} else {
			return false;
		}
	}

	//ta no nome
	public void menos(Data a){
		System.out.println(this.numComRef-a.numComRef); 
	}

	//ta no nome
	private String nomeMes(int a){ //a=mes
		String mes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		return mes[a-1];
	}
	
	//ta no nome
	private static boolean bisSexto(int ano){
		if(ano%400==0 || (ano%4==0 && ano%100!=0)){
			return true;
		} else {
			return false;  
		}
	}

	//valida a data com a referencia
	private boolean dataValida(long refer){
		int dia = converter(2, ref + refer);
		int mes = converter(1, ref + refer);
		int ano = converter(0, ref + refer);
		
		if(ano<1){
			return false;
		}else if(mes>12 || mes<1){
			return false;
		}else if(dia>nDias(ano, mes) || dia<1){
			return false;
		}else{ 
			return true;
		}
	}

	//valida a data já criada
	public boolean dataValida(){//a=dia, b=mes    , c=ano
		int dia = converter(2, ref + numComRef);
		int mes = converter(1, ref + numComRef);
		int ano = converter(0, ref + numComRef);

		if(ano<1){
			return false;
		}else if(mes>12 || mes<1){
			return false;
		}else if(dia>nDias(ano, mes) || dia<1){
			return false;
		}else { 
			return true;
		}
	}
	
	public void vaiParaAmanha(){
		numComRef++;    
	}
	
	public void vaiParaOntem(){
		numComRef--;    
	}

	private static int nDias(int a, int b){
		switch(b){
			case 1: 
				return 31;

			case 2:
				if(bisSexto(a)){
					return 29;
				} else {
					return 28;
				}

			case 3: 
				return 31;
			case 4: 
				return 30;

			case 5: 
				return 31;

			case 6: 
				return 30;

			case 7: 
				return 31;

			case 8: 
				return 31;

			case 9: 
				return 30;

			case 10: 
				return 31;
			
			case 11: 
				return 30;
			
			case 12: 
				return 31;
			
			default:
				return 0;
		}
	}

	public Data getData(){
		return this;
	}

	public long getnumComRef(){
		return numComRef;
	}
}