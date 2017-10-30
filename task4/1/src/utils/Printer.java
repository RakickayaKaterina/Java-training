package utils;

import models.Entity;

public class Printer {
	public static void show(String message) {
		System.out.println(message);
	}

	public static void show(int number) {
		System.out.println(number);
	}

	public static void show(Entity[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
