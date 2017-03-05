import e6.*;
import java.util.*;

public class E306{

	static Scanner k = new Scanner(System.in);

	public static void main(String[] args){

		int opt;
		Data d[] = new Data[2];
		boolean primeira=true;

		do{
			
			System.out.println(" 1. Cria novo objecto com a data actual");
			System.out.println(" 2. Cria novo objecto com uma qualquer data");
			System.out.println(" 3. Indica se a data é válida");
			System.out.println(" 4. Escreve data");
			System.out.println(" 5. Escreve data por extenso");
			System.out.println(" 6. Dia anterior");
			System.out.println(" 7. Dia seguinte");
			System.out.println(" 8. Última data igual à penúltima");
			System.out.println(" 9. Última data maior do que a penúltima");
			System.out.println("10. Última data menor do que a penúltima");
			System.out.println("11. (Última data) - (Penúltima data)");
			System.out.println(" 0. Termina");
			System.out.println("\nNOTA: Se não houver outra indicação, todas as operações fazem-se sobre o último objecto criado");
			System.out.print("\nOpção: ");
			opt=k.nextInt();
			System.out.println();
			
			switch (opt){

				case 1: 
					if(!primeira){
						d[0]=d[1].getData();
					} else {
						primeira = false;
						d[1] = new Data();
						k.nextLine();
					}
					break;
				
				case 2: 
					System.out.print("dia: ");
					int dia = k.nextInt();
					System.out.print("mes: ");
					int mes = k.nextInt();
					System.out.print("ano: ");
					int ano = k.nextInt();
					if(!primeira)
					d[0]=d[1].getData();
					else
					primeira = false;
					d[1] = new Data(ano,mes,dia);
					break;
				
				case 3:
					if(d[1].dataValida()){
						System.out.println("Data válida!");
					}else{
						System.out.println("Data inválida!");
					}
					break;

				case 4: 
					System.out.print("Data: ");
					d[1].dataPrint();
					System.out.println();
					break;
				
				case 5: 
					System.out.println("Data: "+d[1].dataExtenso());
					break;
				
				case 6: 
					d[0]= new Data(d[1].getnumComRef());
					d[1].vaiParaOntem();
					k.nextLine();
					break;
				
				case 7: 
					d[0]= new Data(d[1].getnumComRef());
					d[1].vaiParaAmanha();
					k.nextLine();
					break;
				
				case 8: 
					d[1].dataPrint(); System.out.print(" = "); d[0].dataPrint();
					if(d[1].igual(d[0])){
						System.out.println("? FALSO");
					} else {
						System.out.println("? VERDADEIRO");
					}
					break;
				
				case 9: 
					d[1].dataPrint(); System.out.print(" > "); d[0].dataPrint();
					if(d[1].maior(d[0])){
						System.out.println("? FALSO");
					} else {
						System.out.println("? VERDADEIRO");
					}
					break;
				
				case 10:
						d[1].dataPrint(); System.out.print(" < "); d[0].dataPrint();
					if(d[1].maior(d[0])){
						System.out.println("? VERDADEIRO");
					}else{
						System.out.println("? FALSO");
					}
					break;
				
				case 11:
					d[1].dataPrint(); System.out.print(" - "); d[0].dataPrint();
					System.out.print(" = ");
					d[1].menos(d[0]);
					break;
			
				case 0:
					break;

				default:
					System.out.println("Opção não aceitável");
					break;		
			}
		}while(opt!=0);
	}
}