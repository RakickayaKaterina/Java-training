package com.senla.rakickaya.courseplanner.ui.util.printer;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IEntity;
import com.senla.rakickaya.courseplanner.ui.MenuItem;

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

	public static void showList(List<? extends IEntity> array) {
		for (int i = 0; i < array.size(); i++) {
			show((i + 1) + "." + array.get(i));
		}
	}

}
