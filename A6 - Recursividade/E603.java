import static java.lang.System.*;

public class E603 {

  public static void main(String[] args) {
    printArray(args, 0);
  }

  public static void printArray(String[] array, int N) {
   	if (N < array.length) {
      printArray(array, N+1);
      System.out.println(array[N]);
    }
  }
}