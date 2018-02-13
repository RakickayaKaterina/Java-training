package com.senla.rakickaya.courseplanner.ui.actions.lectors;

import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class SortLectorsByCountCourse implements IAction {
	private ServiceDI service = ServiceDI.getInstance();
	private IFacade facade = (IFacade)service.getObject(IFacade.class);

	@Override
	public void execute() {
		IResponse response = facade.getSortedLectorsByCountCourses();
		@SuppressWarnings("unchecked")
		Map<ILector, Integer> map = (Map<ILector, Integer>) response.getObject(TagsResponse.DATA);
		for (ILector lector : map.keySet()) {
			Printer.show(lector.toString() + " : " + map.get(lector));
		}

	}

}
