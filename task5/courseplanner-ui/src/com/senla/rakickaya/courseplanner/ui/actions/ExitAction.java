package com.senla.rakickaya.courseplanner.ui.actions;

import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.view.facade.Facade;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		facade.save();
		System.exit(0);

	}

}
