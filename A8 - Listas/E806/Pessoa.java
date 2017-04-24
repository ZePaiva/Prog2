package E806;

public class Pessoa implements Comparable<Pessoa>{

	private String name;
	private Data dateOfBirth;

	public Pessoa(String nm, Data data){
		name = nm;
		dateOfBirth = data;
	}

	public Data dateOfBirth(){
		return dateOfBirth;
	}

	public String name(){
		return name;
	}

	public String toString(){
		return ("Nome: " + name + "\nData de nascimento: " + dateOfBirth.day() + "/" + dateOfBirth.month() + "/" + dateOfBirth.year());
	}

	public int compareTo(Pessoa p){
		if ((this.dateOfBirth).month() < (p.dateOfBirth).month()) {
			return -1;
		} else if (((this.dateOfBirth).month() == (p.dateOfBirth).month()) && ((this.dateOfBirth).day() < (p.dateOfBirth).day())) {
			return -1;
		} else if (((this.dateOfBirth).month() == (p.dateOfBirth).month()) && ((this.dateOfBirth).day() == (p.dateOfBirth).day())) {
			return 0;
		} else {
			return 1;
		}
	}


}