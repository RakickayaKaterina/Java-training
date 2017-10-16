import java.util.Random;

public class Generator {
	private int firstNumber;
	private int secondNumber;
	private int thirdNumber;
	private Random r = new Random();

	public void generateNumbers() {
		firstNumber = 100 + r.nextInt(899);
		secondNumber = 100 + r.nextInt(899);
		thirdNumber = 100 + r.nextInt(899);
	}

	private int operate() {
		return (firstNumber * 1000 + secondNumber) - thirdNumber;
	}

	public void show() {
		StringBuilder message = new StringBuilder();
		message.append(firstNumber);
		message.append(" ");
		message.append(secondNumber);
		message.append(" ");
		message.append(thirdNumber);
		message.append(" ");
		message.append(operate());
		System.out.println(message);
	}

}
