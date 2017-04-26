package p2utils;

public class LinkedList<E> {

   /**
    * {@code LinkedList} constructor, empty so far.
    */
   public LinkedList() { }

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
    * @return  Last element in the list
    */
   public E last() {
      assert !isEmpty(): "empty!";
 
      return last.elem;
   }

   /**
    * Adds a new element to the start of the list
    */
   public void addFirst(E e) {
      first = new Node<>(e, first);
      if (isEmpty())
         last = first;
      size++;

      assert !isEmpty() && first().equals(e);
   }

   /**
    * Adds a new element to the end of the list
    */
   public void addLast(E e) {
      Node<E> newest = new Node<>(e);
      if (isEmpty())
         first = newest;
      else
         last.next = newest;
      last = newest;
      size++;

      assert !isEmpty() && last().equals(e);
   }

   /**
    * Removes the first element in the list
    */
   public void removeFirst() {
      assert !isEmpty(): "empty!";
      first = first.next;
      size--;
      if (isEmpty())
         last = null;
   }

   public String toString() {
      if (size()==0) 
         return "[ ]";
      String s = "[ " + first.elem.toString();
      if (size()==1) 
         return s + " ]";
      Node<E> n = first.next;
      while (n!=null) {
         s += ("," + n.elem.toString());
         n = n.next;
      }
      return s + " ]";
   }

   public LinkedList<E> clone(){
    return clone(first);
   }

   private LinkedList<E> clone(Node<E> n){
    if (n == null) {
      return new LinkedList<E>();
    } else {
      LinkedList clonada = clone(n.next);
      clonada.addFirst(n.elem);
      return clonada;
    }
   }

   private Node<E> first = null;
   private Node<E> last = null;
   private int size = 0;
/*---------------------------------------------------------------------------
   Só o que está para baixo desta linha é que interessa
*/

  // count iterativa
  /*public int count(E e){
    int cont = 0;
    for (Node<E> i = first; i.next != null; i = i.next) {
      if (i.elem == e){
        cont++;
      } 
    }
    return cont;  
  }*/

  // count recursiva
  public int count(E e){
    return count(first, e);
  }

  private int count(Node<E> e, E i){
    if (e == null) {
      return 0;
    } else {
      if (e.elem == i) {
        return 1 + count(e.next, i);
      } else {
        return count(e.next, i);
      }
    }
  }

  // indexOf iterativa
  /*public int indexOf(E e){
    int pos = -1;
    int impos = 0;
    for (Node<E> i = first; i != null; i = i.next) {
      if (i.elem == e) {
        pos = impos;
        break;
      }
      impos++;
    }
    return pos;
  }*/

  // indexOf recursiva
  public int indexOf(E e){
    return indexOf(first, e, 0);
  }

  private int indexOf(Node<E> n, E el, int pos){
    if (n == null) {
      return -1;
    } else {
      if ((n.elem).equals(el)) {
        return pos;
      } else {
        return indexOf(n.next, el, pos+=1);
      }
    }
  }

  // cloneReplace iterativa
  /*public LinkedList<E> cloneReplace(E e1, E e2){
    LinkedList<E> clonada = new LinkedList<E>();
    for (Node<E> i = this.first; i != null; i = i.next) {
      if ((i.elem).equals(e1)) {
        clonada.addFirst(e2);
      } else {
        clonada.addFirst(i.elem);
      }
    }
    return clonada;
  }*/


  // cloneReplace recursiva
  public LinkedList<E> cloneReplace(E el1, E el2){
    return cloneReplace(el1, el2, first);
  }

  private LinkedList<E> cloneReplace(E el1, E el2, Node<E> n){
    if (n == null) {
      return new LinkedList<E>();
    } else {
      LinkedList clonada = cloneReplace(el1, el2, n.next);
      if ((n.elem).equals(el1)) {
        clonada.addFirst(el2);
      } else {
        clonada.addFirst(n.elem);
      }
      return clonada;
    }
  }

  // cloneSublist iterativa
  /*public LinkedList<E> cloneSublist(int start, int end){
    assert start >= 1;
    assert end < size;
    int n = 0;
    LinkedList<E> clonada = new LinkedList<E>();
    for (Node<E> i = first; i != null; i = i.next) {
      if (n >= start && n <= end){
        clonada.addFirst(i.elem);
      }
      n++;
    }
    return clonada;
  }*/

  // cloneSublist recursiva
  public LinkedList<E> cloneSublist(int start, int end){
    return cloneSublist(start, end, first, 0);
  } 

  private LinkedList<E> cloneSublist(int start, int end, Node<E> n, int cycles){
    if (cycles == start) {
      return new LinkedList<E>();
    } else {
      LinkedList clonada = cloneSublist(start, end, n, cycles+=1);
      if (cycles <= end && cycles >= start) {
        clonada.addFirst(n.elem);
      }
      return clonada;
    }
  }

  // cloneExceptSubList iterativa
  /*public LinkedList<E> cloneExceptSublist(int start, int end){
    assert start >= 1;
    assert end < size;
    int n = 0;
    LinkedList<E> clonada = new LinkedList<E>();
    for (Node<E> i = first; i != null; i = i.next) {
      if (n < start || n > end){
        clonada.addFirst(i.elem);
      }
      n++;
    }
    return clonada;
  }*/

  // cloneExceptSublist recursiva
  public LinkedList<E> cloneExceptSublist(int start,int end){
    return cloneExceptSublist(start, end, first, 0);
  }

  private LinkedList<E> cloneExceptSublist(int start, int end, Node<E> n, int cycles){
    if (cycles == start) {
      return new LinkedList<E>();
    } else {
      LinkedList clonada = cloneSublist(start, end, n, cycles+=1);
      if (cycles > end || cycles < start) {
        clonada.addFirst(n.elem);
      }
      return clonada;
    }
  }

  // removeSublist iterativa
 /* public void removeSublist(int start, int end){
    assert start >= 1;
    assert end < size;
    int n = 0;
    LinkedList<E> clonada = new LinkedList<E>();
    for (Node<E> i = first; i != null; i = i.next) {
      if (n < start && n > end){
        clonada.addFirst(i.elem);
      }
      n++;
    }
  }
*/
  // removeSublist recursiva
  public void removeSublist(int start, int end){
    first = removeSublist(start, end, first, 0);
    size = end - start;
  }

  private Node<E> removeSublist(int start, int end, Node<E> n, int cycles){
    if (n == null) {
      return  null;
    } else {
      Node<E> recN = removeSublist(start, end, n.next, cycles++);
      if (cycles >= start && cycles < end) {
        return recN;
      } else {
        if (n.next == null) {
          last = n;
        } else {
          return n.next;
        }
      }
    }
    return n;
  }*/
}


