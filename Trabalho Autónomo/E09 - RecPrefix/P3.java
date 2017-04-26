public class P3 {
	public static void main(String[] args) {
		if (args.length % 2 != 0) {
			System.out.print("ERROR: even number of arguments required!");
		} else {
			for (int i = 0; i < args.length-1; i += 2) {
				System.out.printf("\"%s\" is prefixed by \"%s\" -> %s\n", args[i], args[i+1], isPrefix(args[i], args[i+1]) ? "true" : "false");
			}
		}
	}

	public static boolean isPrefix(String prefixed, String post){
		assert prefixed != null;
		assert post != null;
		boolean bool = false;
		if (post.length() == 0) {
			bool = true;
		} else if (post.length() <= prefixed.length() && prefixed.charAt(0) == post.charAt(0)) {
			bool =  isPrefix(prefixed.substring(1), post.substring(1));
		}
		return bool;
	}
}