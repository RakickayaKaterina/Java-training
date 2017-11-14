package com.senla.rakickaya.courseplanner.ui.actions.lectors;

import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;
import com.senla.rakickaya.view.facade.Facade;

public class TotalCountLectors implements IAction {

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		IResponse response = facade.getTotalCountLectors();
		int result = (int) response.getObject(TagsResponse.TOTAL_COUNT);
		Printer.show("Total count lectors: " + result);

	}

}
