package com.senla.rakickaya.courseplanner.ui.actions;

import com.senla.rakickaya.courseplanner.ui.MenuController;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		Printer.show("Aufviedersein");
		MenuController.isRun = false;

	}

}
