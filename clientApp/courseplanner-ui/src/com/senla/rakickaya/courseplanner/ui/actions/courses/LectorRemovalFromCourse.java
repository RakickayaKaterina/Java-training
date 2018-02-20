package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class LectorRemovalFromCourse implements IAction {
	private ServiceDI service = ServiceDI.getInstance();
	private IFacade facade = (IFacade)service.getObject(IFacade.class);

	@Override
	public void execute() {
		Input input = Input.getInstance();

		IResponse responseCourse = facade.getSortedCoursesByLectorName();
		@SuppressWarnings("unchecked")
		List<ICourse> courses = (List<ICourse>) responseCourse.getObject(TagsResponse.DATA);
		Printer.showList(courses);
		Printer.show("Input the number of the course,which you want to remove the lector");
		int positionCourse = input.getInt();
		ICourse course = courses.get(positionCourse-1);
		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_COURSE, String.valueOf(course.getId()))
				.setHead(TagsRequest.ID_LECTOR, String.valueOf(course.getLector().getId())).build();
		IResponse response = facade.removeLectorFromCourse(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());

	}

}
