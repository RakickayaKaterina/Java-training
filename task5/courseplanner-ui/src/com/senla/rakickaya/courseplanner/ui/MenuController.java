package com.senla.rakickaya.courseplanner.ui;

import com.senla.rakickaya.courseplanner.ui.util.input.Input;

public class MenuController {
	private Buider builder;
	private Navigator navigator;
	
	public void run(){
		builder = new Buider();
		builder.buildMenu();
		navigator = new Navigator(builder.getRootMenu());
		while (true) {
			navigator.printMenu();
			int position = Input.getInstance().getInt();
			navigator.navigate(position);
		}
	}
}
