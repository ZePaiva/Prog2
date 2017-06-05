package p2utils;

import p2utils.KeyValueNode;

public class ImprovedKeyValueList<E> {

    private KeyValueNode<E> first = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean set(String key, E elem) {
        int tamanho = size;
        first = set(first, key, elem);

        return size > tamanho;
    }

    private KeyValueNode<E> set(KeyValueNode<E> no, String key, E elem) {
        if(no != null && key.compareTo(no.key) >= 0) {
            if(no.key.equals(key)) {
                no.elem = elem;
            } else {
                no.next = set(no.next, key, elem);
            }
        } else {
            no = new KeyValueNode(key, elem, no);
            size++;
        }

        return no;
    }

    public E get(String key) {
        return get(first, key);
    }

    private E get(KeyValueNode<E> no, String key) {
		if ((no.key).equals(key)) {
			return no.elem;
		} else {
			return get(no.next, key);
		}
    }

    public void remove(String key) {
        first = remove(first, key);
        size--;
    }

    private KeyValueNode<E> remove(KeyValueNode<E> no, String key) {
        if ((no.key).equals(key)) {
            return no.next;
        } else {
            no.next = remove(no.next, key);
            return no;
        }
    }

    public boolean contains(String key) {
        return contains(first, key);
    }

    private boolean contains(KeyValueNode<E> no, String key) {
		if (no != null && key.compareTo(no.key) >= 0) {
			if ((no.key).equals(key)) {
				return true;
			} else {
				return contains(no.next, key);
			}
		} else {
			return false;
		}
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public String[] keys() {
        String[] chaves = new String[size];
        keys(first, 0, chaves);
        return chaves;
    }

    private void keys(KeyValueNode<E> no, int walk, String[] keys) {
        if(no != null) {
            keys[walk] = no.key;
            keys(no.next, walk + 1, keys);
        }
    }

    public String toString() {
        return this.toString("{", ", ", "}");
    }

    public String toString(String start, String mid, String end) {
        String dict = "";

        for(KeyValueNode no = first; no != null; no = no.next) {
            dict += mid + no.toString();
        }

        return start + dict + end;
    }
}
