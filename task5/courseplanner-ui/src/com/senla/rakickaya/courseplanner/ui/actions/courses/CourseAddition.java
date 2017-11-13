package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.HashMap;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;
import com.senla.rakickaya.view.dataExchange.Request;
import com.senla.rakickaya.view.facade.Facade;

public class CourseAddition implements IAction {

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();
		Map<TagsRequest, String> map = new HashMap<>();
		Printer.show("Input course's name : ");
		String name = input.getString();
		map.put(TagsRequest.courseName, name);
		
		Printer.show("Input course's description : ");
		String description = input.getString();
		map.put(TagsRequest.descriptionCourse, description);
		
		Printer.show("Input course's start date : ");
		String startDate = input.getString();
		map.put(TagsRequest.startDateCourse, startDate);
		
		Printer.show("Input course's end date : ");
		String endDate = input.getString();
		map.put(TagsRequest.endDateCourse, endDate);
		
		IRequest request = new Request(map);
		IResponse response = facade.addCourse(request);
		Printer.show(response.getObject(TagsResponse.message).toString());
	}

}
