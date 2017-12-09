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

public class StudentAdditionToCourse implements IAction {
	private ServiceDI service = ServiceDI.getInstance();
	private IFacade facade = (IFacade)service.getObject(IFacade.class);

	@Override
	public void execute() {
		Input input = Input.getInstance();

		IResponse responseCourse = facade.getAllCourses();
		@SuppressWarnings("unchecked")
		List<ICourse> courses = (List<ICourse>) responseCourse.getObject(TagsResponse.DATA);
		Printer.showList(courses);
		Printer.show("Input the number of the course,which you want to add the student");
		int positionCourse = input.getInt();

		IResponse responseStudent = facade.getAllStudents();
		@SuppressWarnings("unchecked")
		List<IStudent> students = (List<IStudent>) responseStudent.getObject(TagsResponse.DATA);
		Printer.showList(students);
		Printer.show("Input the number of the student to addition");
		int positionStudent = input.getInt();

		IRequest request = new RequestBuilder()
				.setHead(TagsRequest.ID_COURSE, String.valueOf(courses.get(positionCourse - 1).getId()))
				.setHead(TagsRequest.ID_STUDENT, String.valueOf(students.get(positionStudent - 1).getId())).build();
		IResponse response = facade.addStudentToCourse(request);
		Printer.show(response.getObject(TagsResponse.MESSAGE).toString());
	}

}
