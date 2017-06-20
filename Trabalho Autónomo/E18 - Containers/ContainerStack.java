//NMEC: 
//NOME: 
import static java.lang.System.*;

public class ContainerStack {
  private Container[] array;
  private int size;  
  
  public ContainerStack(int maxSize) {
    assert maxSize >= 0;
    array = new Container[maxSize];
    //size = 0;
    assert isEmpty();
  }
  
  public void push(Container e) {
    assert !isFull();
    array[size] = e;
    size++;
    e.incrementOps();
    assert !isEmpty() && top() == e;
  }
  
  public void pop() {
    assert !isEmpty();
    --size;
    array[size] = null;
    assert !isFull();
  }
  
  public Container top() {
    assert !isEmpty();
    return array[size-1];
  }
  
  public boolean isEmpty() { return size==0; }
  
  public boolean isFull() { return size==array.length; }
  
  public int size() { return size; }
  
  public void clear() {
    size = 0;
    array = new Container[array.length];
    assert isEmpty();
  }
  
  /**
   * Search for a container with a given type of cargo.
   * @param type the type of cargo we're looking for
   * @return 0 if the desired container is on the top,
   *         1 if it is under 1 container, etc
   *        -1 if not found
   */
  public int search(String type) {
    for (int i = 0; i < size; i++) {
      if (array[size-1-i].contains(type)) return i;
    }
    return -1;
  }
  

  public int searchRec(String type){
  	return searchRec(type, 0);
  }

  private int searchRec(String type, int pos){
  	if (pos == size) {
  		return -1;
  	} else if (array[pos].contains(type)){
  		return pos;
  	} else {
  		return searchRec(type, pos+1);
  	}
  }
  
  /**
   * Merge two sorted subvectors: a[start..middle[ with a[middle..end[
   */
  private static void mergeSubarrays(Container[] a,int start,int middle,int end) 
  {
    Container[] b = new Container[end-start];
    int i1 = start;
    int i2 = middle;
    int j = 0;
    while(i1 < middle && i2 < end) {
      if (a[i1].id < a[i2].id)
        b[j++] = a[i1++];
      else
        b[j++] = a[i2++];
    }
    while(i1 < middle)
      b[j++] = a[i1++];
    while(i2 < end)
      b[j++] = a[i2++];
    arraycopy(b, 0, a, start, end-start);
  }

  public String toString(){
  	String toRet = "";
  	for (int i = 0; i < size; i++) {
  		toRet +=  array[i].toString() + ",";
  	}
  	return toRet;
  }

  public static void sort(Container[] array, int start, int end){
  	if (end - start > 1) {
  		int mid = (end + start) / 2;
  		sort(array, start, mid);
  		sort(array, mid, end);
  		mergeSubarrays(array, start, mid, end);
  	}
  }

  public Container[] containersInStack(){
  	Container[] toRet = new Container[size];
  	System.arraycopy(array, 0, toRet, 0, size);
  	this.sort(toRet, 0, size);
  	return toRet;
  }
}
