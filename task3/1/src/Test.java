public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Hello, I am Kate.       I wrote this class .";
		Parser parser = new Parser(text);
		System.out.println("Words: ");
		for (String string : parser.parse()) {
			System.out.println(string);
		}
	}
}
