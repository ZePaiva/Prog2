//NMEC: 
//NOME: 
import static java.lang.System.*;

public class ContainerTerminal {
  ContainerStack[] sa;    // the stacks of containers
  int num;  // number of parallel stacks of containers
  int max;  // maximum height of each stack of containers
  int numcontainers;  // number of containers on the terminal
  
  public ContainerTerminal(int num, int max) {
    sa = new ContainerStack[num];
    for (int k = 0; k < num; k++) {
      sa[k] = new ContainerStack(max);
    }
    this.num = num;
    this.max = max;
    numcontainers = 0;
  }
  
  /**
   * Is the terminal full?
   * A full terminal must still have enough free space to enable
   * retrieving any given container.
   */
  public boolean isFull() {
    return numcontainers >= num*max - max;
  }
  
  /**
   * Checks if a container of a certain type exists
   */
  public boolean containerTypeExists(String type) {
    return findStackContaining(type) >= 0;
  }

  
  /**
   * Find first stack with free space, other than the stackToAvoid.
   * @return the index of the found stack
   */
  private int findOtherStack(int stackToAvoid) {
    assert !isFull();
    int k = 0;
    while (k==stackToAvoid || sa[k].isFull()) {
      k++;
    }
    return k;
  }
  
  /**
   * Find a stack that includes a container with a given type of cargo
   * @return the index of the found stack, or -1 if no such cargo exists.
   */
  private int findStackContaining(String type) {
    int k = 0;
    while (k < num) {
      if (sa[k].search(type) >= 0) return k;
      k++;
    }
    return -1;
  }
  
  public void print() {
    out.printf("Terminal numcontainers=%d isFull=%s\n",
                      numcontainers, isFull());
    for (int k = 0; k < num; k++) {
      out.printf("%2d: %s\n", k, sa[k].toString());
    }
  }

  // Log: historical log of retrieved containers: it's a linked list
  HistoryNode log;

  private void logContainerInfo(Container c) {
    HistoryNode n = new HistoryNode();
    //n.type = c.type();
    n.numops = c.numops();
    n.next = log;
    log = n;
  }
  
  public void store(Container c){
  	assert !isFull() : "Full terminal";
  	int stack;
  	if (sa[0].isFull()) {
  		stack = findOtherStack(0);	
  	} else {
  		stack = 0;
  	}
  	sa[stack].push(c);
  	numcontainers++;
  }

  public Container retrieve(String type){
  	int stack = findStackContaining(type);
  	Container tmp;
  	if (stack == -1) {
  		return null;
  	} else {
  		while (true){
	  		tmp = sa[stack].top();
	  		sa[stack].pop();
  			if ((tmp.type).equals(type)) {
  				break;
  			} else {
  				int stk = findOtherStack(stack);
  				sa[stk].push(tmp);
  			}
  		}
  		logContainerInfo(tmp);
  		numcontainers--;
  		return tmp;
  	}
  }

  public double averageOpsPerContainer(){
  	double[] db = averageOpsPerContainer(log, 1); 
  	return db[0] / db[1];
  }

  private double[] averageOpsPerContainer(HistoryNode node, double counts){
  	if (node.next == null) {
  		double[] np = {node.numops, counts};
  		return np;
  	} else {
  		double[] np = averageOpsPerContainer(node.next, counts+1);
  		double[] np2 = {np[0] + node.numops , np[1]}; 
  		return np2;
  	}
  }
}
