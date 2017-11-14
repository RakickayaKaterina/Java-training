package com.senla.rakickaya.courseplanner.ui.actions.courses;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
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

public class StudentRemovalFromCourse implements IAction {

	@Override
	public void execute() {
		Input input = Input.getInstance();
		IFacade facade = Facade.getInstance();

		IResponse responseCourse = facade.getAllCourses();
		List<ICourse> courses = (List<ICourse>) responseCourse.getObject(TagsResponse.DATA);
		Printer.showList(courses);
		Printer.show("Input the number of the course,which you want to remove the student");
		int positionCourse = input.getInt();

		IResponse responseStudent = facade.getAllCourses();
		List<IStudent> students = (List<IStudent>) responseStudent.getObject(TagsResponse.DATA);
		Printer.showList(students);
		Printer.show("Input the number of the student to removal");
		int positionStudent = input.getInt();

		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_COURSE, String.valueOf(courses.get(positionCourse - 1).getId()))
				.setHead(TagsRequest.ID_STUDENT, String.valueOf(students.get(positionStudent - 1).getId())).build();
		IResponse response = facade.removeStudentFromCourse(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());

	}

}
