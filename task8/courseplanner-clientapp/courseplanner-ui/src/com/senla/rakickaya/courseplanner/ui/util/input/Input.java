package com.senla.rakickaya.courseplanner.ui.util.input;

import java.io.InputStream;
import java.util.Scanner;

public class Input {
	private static Input input;

	public static Input getInstance() {
		if (input == null) {
			input = new Input(System.in);
		}
		return input;
	}

	private Scanner scanner;

	private Input(InputStream inputStream) {
		super();
		this.scanner = new Scanner(inputStream);
	}

	public String getString() {
		return scanner.nextLine();
	}

	public int getInt() {
		return Integer.valueOf(scanner.nextLine());
	}

	@Override
	protected void finalize() throws Throwable {
		scanner.close();
		super.finalize();
	}
}
