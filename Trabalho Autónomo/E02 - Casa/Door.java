public class Door {

	private int i1, i2;
	private double larg, alt;

	public Door(int id1, int id2, double width, double height) {

		i1 = id1;
		i2 = id2;
		larg = width;
		alt = height;
	}

	public double area(){
		return alt * larg;
	}

	public int roomId1(){
		return i1;
	}

	public int roomId2(){
		return i2;
	}
}