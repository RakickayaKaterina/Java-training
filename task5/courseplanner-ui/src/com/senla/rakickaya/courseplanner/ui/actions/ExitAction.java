package com.senla.rakickaya.courseplanner.ui.actions;

import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.facade.Facade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		facade.save();
		System.exit(0);

	}

}
