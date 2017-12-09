package com.senla.rakickaya.courseplanner.ui;

import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;

public class MenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	public void doAction() {
		action.execute();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

}
