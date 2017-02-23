package pt.ua.prog2;

public class Contacto{

	private String nome;
	private String telefone;
	private String mail;
	public static int contactos;

	public Contacto(String nm, String tel, String ml){
		nome = nm;
		telefone = tel;
		mail = ml;
		contactos++;
	}
	
	public Contacto(String nm, String tel){
		nome = nm;
		telefone = tel;
		mail = "";	
		contactos++;
	}
	
	public String nome(){
		return nome;
	}
	
	public String telefone(){
		return telefone;
	}
	
	public String eMail(){
		return mail;
	}

	public void printCont(){
		System.out.printf("%s: %s; %s\n", nome, telefone, mail);
	}
}