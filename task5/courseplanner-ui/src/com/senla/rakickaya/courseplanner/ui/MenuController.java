package com.senla.rakickaya.courseplanner.ui;

public class MenuController {
	private Buider builder;
	private Navigator navigator;
	
	public void run(){
		builder = new Buider();
		builder.buildMenu();
		navigator = new Navigator(builder.getRootMenu());
		//TODO run
	}
}
