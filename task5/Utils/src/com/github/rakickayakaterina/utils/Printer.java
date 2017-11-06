package com.github.rakickayakaterina.utils;

import java.util.List;

import com.github.rakickayakaterina.model.beans.Entity;

public class Printer {
	public static void show(String message) {
		System.out.println(message);
	}

	public static void show(int number) {
		System.out.println(number);
	}

	public static void show(List<Entity> array) {
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
	}
}
