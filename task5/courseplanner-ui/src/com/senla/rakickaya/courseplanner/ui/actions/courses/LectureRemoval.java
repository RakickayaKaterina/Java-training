package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.facade.Facade;
import com.senla.rakickaya.courseplanner.facade.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.ui.api.actions.IAction;
import com.senla.rakickaya.courseplanner.ui.util.input.Input;
import com.senla.rakickaya.courseplanner.ui.util.printer.Printer;

public class LectureRemoval implements IAction {

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();

		IResponse responseCourse = facade.getAllCourses();
		List<ICourse> courses = (List<ICourse>) responseCourse.getObject(TagsResponse.DATA);
		Printer.showList(courses);
		Printer.show("Input the number of the course");
		int positionCourse = input.getInt();

		IResponse responseLecture = facade.getLecturesByCourse(positionCourse);
		List<ILecture> lectures = (List<ILecture>) responseLecture.getObject(TagsResponse.DATA);
		Printer.showList(lectures);
		Printer.show("Input the number of the lecture to remove");
		int positionLecture = input.getInt();

		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_LECTURE, String.valueOf(lectures.get(positionLecture - 1).getId()))
				.setHead(TagsRequest.ID_COURSE, String.valueOf(lectures.get(positionLecture - 1).getId())).build();
		IResponse response = facade.removeLectureFromCourse(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
