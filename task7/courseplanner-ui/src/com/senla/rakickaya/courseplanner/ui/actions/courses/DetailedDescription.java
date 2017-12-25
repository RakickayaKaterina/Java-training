package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.di.ServiceDI;
import com.senla.rakickaya.courseplanner.facade.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class DetailedDescription implements IAction {
	private ServiceDI service = ServiceDI.getInstance();
	private IFacade facade = (IFacade)service.getObject(IFacade.class);

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IResponse response = facade.getAllCourses();
		@SuppressWarnings("unchecked")
		List<ICourse> list = (List<ICourse>) response.getObject(TagsResponse.DATA);
		Printer.showList(list);
		Printer.show("Input the number to get detail description the course");
		int position = input.getInt();
		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_COURSE, String.valueOf(list.get(position - 1).getId())).build();
		IResponse courseResponse = facade.getDetailedDescription(request);
		Printer.show(courseResponse.getObject(TagsResponse.MESSAGE).toString());
		@SuppressWarnings("unchecked")
		List<IStudent> listStudent = (List<IStudent>) courseResponse.getObject(TagsResponse.DATA);
		Printer.show("Students: ");
		Printer.showList(listStudent);
	}

}
