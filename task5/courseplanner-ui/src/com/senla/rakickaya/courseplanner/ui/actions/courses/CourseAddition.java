package com.senla.rakickaya.courseplanner.ui.actions.courses;


import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;
import com.senla.rakickaya.view.dataExchange.RequestBuilder;
import com.senla.rakickaya.view.facade.Facade;

public class CourseAddition implements IAction {

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();
		
		Printer.show("Input course's name : ");
		String name = input.getString();
		
		Printer.show("Input course's description : ");
		String description = input.getString();
		
		Printer.show("Input course's start date : ");
		String startDate = input.getString();
		
		Printer.show("Input course's end date : ");
		String endDate = input.getString();
		
		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.COURSE_NAME, name)
				.setHead(TagsRequest.DESCRIPTION_COURSE, description)
				.setHead(TagsRequest.START_DATE_COURSE, startDate)
				.setHead(TagsRequest.END_DATE_COURSE, endDate)
				.build();
		IResponse response = facade.addCourse(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
