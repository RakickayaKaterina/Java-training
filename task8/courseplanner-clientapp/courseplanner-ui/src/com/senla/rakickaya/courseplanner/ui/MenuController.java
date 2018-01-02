package com.senla.rakickaya.courseplanner.ui;

import com.senla.rakickaya.courseplanner.ui.util.input.Input;

public class MenuController {
	public static boolean isRun = true;
	private Buider builder;
	private Navigator navigator;
	
	public void run(){
		builder = new Buider();
		builder.buildMenu();
		navigator = new Navigator(builder.getRootMenu());
		while (isRun) {
			navigator.printMenu();
			int position = Input.getInstance().getInt();
			navigator.navigate(position);
		}
	}
}
