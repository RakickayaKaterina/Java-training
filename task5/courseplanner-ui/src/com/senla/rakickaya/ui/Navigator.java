package com.senla.rakickaya.ui;

import java.util.List;

import com.senla.rakickaya.printer.Printer;

public class Navigator {
	private Menu currentMenu;

	public Navigator(Menu currentMenu) {
		super();
		this.currentMenu = currentMenu;
	}

	public void printMenu() {
		Printer.showMenu(currentMenu.getListItems());
	}
	public void navigate(Integer index){
		List<MenuItem> listItems = currentMenu.getListItems();
		MenuItem item = listItems.get(index);
		if(item.getAction()!=null){
			item.doAction();
		}
		else{
			currentMenu = item.getNextMenu();
		}
	}
}
