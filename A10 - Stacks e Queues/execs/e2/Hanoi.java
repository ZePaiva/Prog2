import p2utils.*;
import static java.lang.System.*;

public class Hanoi{
	private Stack<Integer> T1;
	private Stack<Integer> T2;
	private Stack<Integer> T3;
	private int moves = 0;

	public int mvs(){
		return moves;
	}	

	public Hanoi(int discs){
		T1 = new Stack();
		T2 = new Stack();
		T3 = new Stack();
		for (int i = discs; i >= 1; i--){
			putDisk(T1, i);
		}
	}

	private void putDisk(Stack<Integer> tower, int disc){
		tower.push(disc);
	}

	private int takeDisk(Stack<Integer> tower){
		int disk = tower.top();
		tower.pop();
		return disk;
	}

	public void solve(){
		moves = 0;
		printMove();
		solve(T1.size(), T1, T2, T3);

	}

	private void solve(int discs, Stack<Integer> T1, Stack<Integer> T2, Stack<Integer> T3){
		if (discs >=1 ){
			solve(discs-1, T1, T3, T2);
			move(T1, T3);
			solve(discs-1, T2, T1, T3);
		}
	}

	private void move(Stack<Integer> T1, Stack<Integer> T2){
		putDisk(T2, takeDisk(T1));
		moves++;
		printMove();
	}

	private void printMove(){
		out.printf("Move %d:\n", moves);
		out.println("T1: " + T1.toStringReverse());
		out.println("T2: " + T2.toStringReverse());
		out.println("T3: " + T3.toStringReverse());
	}
}
