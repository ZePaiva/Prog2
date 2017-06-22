
import p2utils.*;

public class Dock {

   private final Stack<Container> containerStack;
   private final Queue<Container> accessExtension;
   private final int maxStack; // maximum number of containers in a stack

   public Dock(int maxStack) {
      accessExtension = new Queue<>();
      containerStack = new Stack<>();
      this.maxStack = maxStack; // controla-se o size fora da pilha
   }

   public boolean stackEmpty() {
      return containerStack.isEmpty();
   }

   public boolean stackFull() {
      return containerStack.size()==maxStack;
   }

   public boolean accessExtensionEmpty() {
      return accessExtension.isEmpty();
   }

   public int count() {
      return containerStack.size()+accessExtension.size();
   }

   public String toString() {
      return "In stack:" + containerStack.toString() + 
          " / In access: " + accessExtension.toString();
   }

   // a new container arrives and must be added to the queue
   public void enterContainer(Container c) {
      accessExtension.in(c);

   }
    
   // top container in stack is removed (it will be shipped)
   public Container shipContainer() {
      Container tmp = containerStack.top();
      containerStack.pop();
      return tmp;
   }

   // Moves containers from the accessExtension to the dock stack
   public void moveFromAccessToStack() {
      while (!accessExtension.isEmpty() && containerStack.size() < maxStack) {
         containerStack.push(accessExtension.peek());
         accessExtension.out();
      }
   }

   // pick the first container from the queue
   // and insert it in the stack in such way the stack
   // is sorted by distance to destination
   // (largest distance at the top)
   public void insertFirstInStack() {
      assert !stackFull() && !accessExtensionEmpty();
      Container tempContainer = accessExtension.peek();
      accessExtension.out();
      Stack<Container> tempStack = new Stack<Container>();

      while(!containerStack.isEmpty() && containerStack.top().distance > tempContainer.distance){
         tempStack.push(containerStack.top());
         containerStack.pop();
      }

      containerStack.push(tempContainer);

      while(!tempStack.isEmpty()){
         containerStack.push(tempStack.top());
         tempStack.pop();
      }
   }
}

