public class Test {

	public static void main(String[] args) {
		String text = "Hello, I am Kate.       I wrote this class .";
		System.out.println("Words: ");
		for (String string : parse(text)) {
			System.out.println(string);
		}
	}

	public static String[] parse(String text) {
		return text.split("[\\p{P}\\s]+");
	}
}
