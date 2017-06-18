/**
 * Complete este módulo de forma a que o programa principal funcione devidamente.
 * 
 * Uma CompressedQueue é uma fila, mas onde entradas cvom o mesmo valor são comprimidas
 * para um único elemento da fila contendo o valor e o número de repetições.
 * 
 * Além disto e dos métodos usuais, tem também métodos adicionais:
 * 
 * peekCount()
 *   Devolve o número de repetições do valor existente na frente da fila.
 * 
 * maxDiff()
 *   Indica qual o maior "salto" (em valor absoluto) entre elementos consecutivos da fila.
 * 
 * minDiff()
 *   Indica qual o menor "salto" (em valor absoluto) entre elementos consecutivos da fila.
 * 
 * toString()
 *   Devolve uma string com os elementos da fila (entre {}), por ordem, em cada elemento
 *   é descrito pelo par valor e repetições (entre []) e cada elemento é por
 *   vírgulas.
 * 
 **/

// node class for queue
class CompressedQueueNode {
	int elem;
	int elemCount;
	CompressedQueueNode next;

	public CompressedQueueNode(int elem, int elemCount){
		this.elem = elem;
		this.elemCount = elemCount;
		next = null;
	}
}

// queue
public class CompressedQueue {
	private int size;
	private CompressedQueueNode first;
	private CompressedQueueNode last;

	public CompressedQueue(){
		first = last = null;
		size = 0;
	}

	public int size(){
		return size;
	}

	public void in(int number){
		in(number, first);
	}

	private void in(int number, CompressedQueueNode node){
		size++;
		if (first == null) {
			CompressedQueueNode tmp = new CompressedQueueNode(number, 1);
			tmp.next = null;
			first = tmp;
			last = tmp;
		} else {
			if (node == null) {
				CompressedQueueNode tmp = new CompressedQueueNode(number, 1);
				last.next = tmp;
				tmp.next = null;
				last = tmp;
			} else {
				if (node.elem == number) {
					node.elemCount++;
				} else {
					in(number, node.next);
				}
			}
		}
	}

	public boolean empty(){
		return first == null;
	}

	public void out(){
		assert !empty() : "fila vazia";
		if (first.elemCount > 1) {
			first.elemCount--;
		} else {
			first = first.next;
		}
	}

	public int peek(){
		return first.elem;
	}

	public int peekCount(){
		return first.elemCount;
	}

	public String toString(){
		String s = "{";

		for (CompressedQueueNode i = first; i != null; i = i.next) {
			if (!i.equals(first)) {
				s += ", ";
			}
			s += "[" + i.elem + ":" + i.elemCount + "]";
		}
		return (s + "}");
	}

	public int maxDiff(){
		assert !empty() : "fila vazia";
		int max = 0;
		for (CompressedQueueNode i = first; i.next != null; i = i.next) {
			if (Math.abs(i.elem - (i.next).elem) > max) {
				max = Math.abs(i.elem - (i.next).elem);
			}
		}
		return max;
	}

	public int minDiff(){
		assert !empty() : "fila vazia";
		int min = Integer.MAX_VALUE;
		for (CompressedQueueNode i = first; i.next != null; i = i.next) {
			if (Math.abs(i.elem - (i.next).elem) < min) {
				min = Math.abs(i.elem - (i.next).elem);
			}
		}
		return min;
	}

	public void clear(){
		first = last = null;
		size = 0;
	}
}