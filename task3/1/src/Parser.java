
public class Parser {
	private String text;

	public Parser(String text) {
		this.text=text;
	}
	public String[] parse(){
		return text.split("[\\p{P}\\s]+");
	}
}
