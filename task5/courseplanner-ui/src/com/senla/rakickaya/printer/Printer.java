package com.senla.rakickaya.printer;

import java.util.List;

import com.senla.rakickaya.ui.MenuItem;

public class Printer {
	public static void show(String message) {
		System.out.println(message);
	}

	public static void show(List<String> list) {
		for (String string : list) {
			show(string);
		}
	}

	public static void showMenu(List<MenuItem> items) {
		for (int i = 0; i < items.size(); i++) {
			show((i + 1) + ". " + items.get(i).getTitle());
		}

	}
}
