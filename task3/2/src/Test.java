
public class Test {

	public static void main(String[] args) {
		Operation operation = new Operation();
		int firstNumber = operation.generateNumber();
		int secondNumber = operation.generateNumber();
		int thirdNumber = operation.generateNumber();
		int result = operation.subtract(operation.concatenate(firstNumber, secondNumber), thirdNumber);
		
		Printer.show("Output:");
		Printer.show(firstNumber);
		Printer.show(secondNumber);
		Printer.show(thirdNumber);
		Printer.show(result);
		
		
	}
	
}
