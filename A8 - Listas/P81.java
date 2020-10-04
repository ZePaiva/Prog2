import static java.lang.System.*;

public class P81{

	public static void main(String[] args){

		double[] arr = new double[args.length];
		
		for (int i = 0; i < args.length; i++) {
			arr[i] = Double.parseDouble(args[i]);
		}

		out.printf("sum of arguments = %f\n", sumRec(arr, 0, arr.length));
		
		// Verificação de correção:
		assert sumRec(arr, 0, arr.length) == sum(arr, 0, arr.length);
	}

	// sum of subarray [start,end[ of arr:
	public static double sum(double[] arr, int start, int end){

		assert arr != null;
		//Changed to end < arr.length since it would be an invalid value
		assert start >= 0 && start <= end && end < arr.length;

		double res = 0;
		for(int i = start; i < end; i++){
			res += arr[i];
		}
		return res;
	}

	// same thing, recursive:
	public static double sumRec(double[] arr, int start, int end){
		assert start == 0 && start < end;

		if (start == end-1) {
			return arr[start];
		} else {
			return arr[end-1] + sumRec(arr, start, end-1);
		}
	}
}
