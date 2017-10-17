import java.util.Random;

public class Operation {
	private Random r = new Random();

	public int generateNumber() {
		return 100 + r.nextInt(899);
	}

	public int concatenate(int op1, int op2) {
		return op1 * 1000 + op2;
	}
	public int subtract(int op1,int op2){
		return op1-op2;
	}
	

}
