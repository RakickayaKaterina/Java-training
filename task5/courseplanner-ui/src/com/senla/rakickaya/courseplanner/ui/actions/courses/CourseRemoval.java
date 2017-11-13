package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
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

public class CourseRemoval implements IAction{

	@Override
	public void execute() {
		IFacade facade = Facade.getInstance();
		IResponse response = facade.getAllCourses();
		List<ICourse> courses = (List<ICourse>) response.getObject(TagsResponse.data);
		Printer.showList(courses);
		Input input = Input.getInstance();
		int n = input.getInt();
		Map<TagsRequest,String> map = new HashMap<>();
		map.put(TagsRequest.idCourse, String.valueOf(courses.get(n).getId()));
		IRequest request =  new Request(map);
		IResponse courseResponse = facade.removeCourse(request);
		Printer.show(courseResponse.getObject(TagsResponse.message).toString());
		
	}

}
