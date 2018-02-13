package com.senla.rakickaya.courseplanner.ui.actions.timetable;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
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

public class LessonAddition implements IAction {
	private ServiceDI service = ServiceDI.getInstance();
	private IFacade facade = (IFacade) service.getObject(IFacade.class);

	@Override
	public void execute() {
		Input input = Input.getInstance();

		IResponse response = facade.getAllCourses();
		@SuppressWarnings("unchecked")
		List<ICourse> courses = (List<ICourse>) response.getObject(TagsResponse.DATA);
		Printer.showList(courses);
		Printer.show("Input the number of the course to create time table");
		int positionCourse = input.getInt();

		List<ILecture> lectures = courses.get(positionCourse - 1).getLectures();
		Printer.showList(lectures);
		Printer.show("Input the number of the lecture to create time table");
		int positionLecture = input.getInt();

		Printer.show("Input the date for lecture : ");
		String date = input.getString();

		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_LECTURE, String.valueOf(lectures.get(positionLecture - 1).getId()))
				.setHead(TagsRequest.DATE, date).setHead(TagsRequest.COUNT_STUDENT,
						String.valueOf(courses.get(positionCourse - 1).getStudents().size()))
				.build();
		IResponse responseLesson = facade.createTimeTableForLecture(request);
		Printer.show(responseLesson.getObject(TagsResponse.MESSAGE).toString());

	}
}
