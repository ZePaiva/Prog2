package p2utils;

import p2utils.*;

public class SortedList<E extends Comparable<E>> {

	// private attributes
	private Node<E> first = null;
	private int size = 0;

	public SortedList() { }

	/**
	* @return Number of elements in the list
	*/
	public int size() { return size; }

	/**
	* Checks if the list is empty
	* @return  {@code true} if list empty, otherwise {@code false}.
	*/
	public boolean isEmpty() { return size == 0; }

	/**
	* @return  First element in the list
	*/
	public E first() {
		assert !isEmpty(): "empty!";
		return first.elem;
	}

	/**
	* Inserts a new element in the list
	* @param e the element to be inserted
	*/
	public void insert(E e) {
		first = insert(first,e);
		size++;
	}
	private Node<E> insert(Node<E> n,E e) {
		if (n ==null || e.compareTo(n.elem) < 0){
			return new Node<E>(e,n);
		}
		n.next = insert(n.next,e);
		return n;
	}

	/**
	* Removes the first element in the list
	*/
	public void removeFirst() {
		assert !isEmpty(): "empty!";
		assert isSorted(): "not sorted!";
		first = first.next;
		size--;

		assert isSorted();
	}

	/**
	* Checks if the list is sorted
	* @return {@code true} if sorted, {@code false} otherwise
	*/
	public boolean isSorted() { 
		if (size < 2)
			return true;
		return isSorted(first,first.next); 
	}

	private boolean isSorted(Node<E> prev,Node<E> n) {
		if (n == null) return true;
		if (((Comparable)n.elem).compareTo(prev.elem) < 0) return false;
		return isSorted(n,n.next);
	}

	/**
	* Checks if the given element exists in the list
	* @param e an element
	* @return {@code true} if the element exists and {@code false} otherwise
	*/
	public boolean contains(E e) { 
		return contains(first,e); 
	}
	private boolean contains(Node<E> n,E e) {
		if (n != null && e.compareTo(n.elem) >= 0){
			if ((n.elem).equals(e)) {
				return true;
			} else {
				return contains(n.next, e);
			}
		} else {
			return false;
		}
	}

	public String toString() {
		if (size == 0) {
			return "[ ]";
		} else {
			String inicial = "[" + first.elem;
			if (size == 1) {
				return (inicial + "]");
			} else {
				for (Node i = first.next; i != null; i=i.next) {
					inicial += "," + i.elem;
				}
				inicial += "]";
			}

			return inicial;
		}
	}

	public SortedList<E> merge(SortedList<E> lista) {
		SortedList<E> merged = lista.clone();
		merge(first, merged);
		return merged;
	}

	private void merge(Node<E> no, SortedList<E> lista){
		if (no != null) {
			lista.insert(no.elem);
			merge(no.next, lista);
		}
	}

	public SortedList<E> clone(){
		return clone(first);
	}

	private SortedList<E> clone(Node<E> no){
		if (no == null) {
			return new SortedList();
		} else {
			SortedList<E> lista = clone(no.next);
			lista.insert(no.elem);
			return lista;
		}
	}

	/** Prints all elements, one per line. */
	public void print() {
		print(first);
	}
	private void print(Node<E> n) {
		if (n != null) {
			System.out.println(n.elem);
			print(n.next);
		}
	}
}