package p2utils;

import static java.lang.System.*;
import p2utils.BinarySearchTreeNode;

public class BinarySearchTree<E>{

  private BinarySearchTreeNode<E> raiz = null;
  private int size = 0;

  public BinarySearchTree() { }

  public void set(String key,E elem) {
    assert key != null;

    // ........
    BinarySearchTreeNode rootOfThis = null;
    if (raiz != null) {
      rootOfThis = raiz.search(key);
    }

    // se já existe a chave atualiza, caso contrário cria uma nova chave 
    if (rootOfThis != null) {
      rootOfThis.elem = elem;
    } else {
      rootOfThis = new BinarySearchTreeNode(key, elem);
      raiz = rootOfThis.insertLeaf(raiz);
      size++;
    }

    assert contains(key);
    assert get(key) == elem;
  }

  public E get(String key) {
    assert key != null;
    assert contains(key);

    return raiz.search(key).elem; 

  }

  public void remove(String key) {
    assert key != null;
    assert contains(key);

    raiz = raiz.removeRecursive(key);
    size--;

    assert !contains(key);
  }

  public boolean contains(String key) {
    assert key != null;

    return raiz != null && raiz.contains(key);
  }

  public boolean isEmpty() {
    return raiz == null; 
  }

  public int size() {
    return size;
  }

  public void clear() {
    raiz = null;
    size = 0;
  }

  public String[] keys() {
    String[] chaves = new String[size];
    if (raiz != null) {
      raiz.arrayKeys(chaves, 0);
    }
    return chaves;
  }

  public String toString() {
    if (raiz != null) {
      return raiz.toString();
    } else {
      return "";
    }
  }

}

