package p2utils;

public class SortedArray<E extends Comparable<E>>{
	
	private E array[];
	private int size = 0;
	private int fullness = 0;

	public SortedArray(int tamanho){
		size = tamanho;
		array = (E[])new Comparable[size];
	}

	public int size(){
		return size;
	}

	public int fullness(){
		return fullness;
	}

	public boolean isFull(){
		return (fullness == size);
	}

	public boolean isEmpty(){
		return (fullness == 0);
	}

	public void insert(E tipo){
		if (isFull()) {
			System.out.println("Can't fill more");
		} else if (isEmpty()) {
			array[0] = tipo;
		} else {
			for (int i = 0; i < fullness; i++){
				if (array[i].compareTo(tipo) <= 0){
					for(int j = fullness; j == i+1; j--){
						array[j] = array[j-1];
					}
					array[i] = tipo;
					break;
				}
			}
			fullness++;
		}	
	}

	public void removeFirst(){
		assert !isEmpty() : "no first element";
		for (int i = 0; i < fullness-1; i++) {
			array[i] = array[i+1];
		}
		array[fullness-1] = null;
	}

	public boolean contains(E tipo){
		for (int i = 0; i < fullness-1; i++) {
			if (array[i] == tipo) {
				return true;
			}
		}
		return false;
	}

	public String toString(){
		if (fullness == 0) {
			return "[]";
		}else {
			String init = "["+ array[0];
			if (fullness == 1) {
				return (init + "]");
			} else {
				for (int i = 1; i < fullness; i++) {
					init += ", " + array[i];
				}
				return (init + "]");
			}
		}
	}

	public void print(){
		for (int i = 0 ; i < fullness; i++) {
			System.out.println(array[i]);
		}
	}
}
