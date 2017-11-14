package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
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

public class LectureAddition implements IAction {

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();

		Printer.show("Input lecture's name : ");
		String name = input.getString();

		IResponse response = facade.getAllCourses();
		List<ICourse> courses = (List<ICourse>) response.getObject(TagsResponse.DATA);
		Printer.showList(courses);
		Printer.show("Input the number of the course,which you want to add new lecture");
		int position = input.getInt();

		IRequest request = new RequestBuilder().setHead(TagsRequest.LECTURE_NAME, name)
				.setHead(TagsRequest.ID_COURSE, String.valueOf(courses.get(position - 1).getId())).build();
		IResponse responseLecture = facade.addLectureToCourse(request);
		Printer.show(responseLecture.getObject(TagsResponse.MESSAGE).toString());

	}

}
