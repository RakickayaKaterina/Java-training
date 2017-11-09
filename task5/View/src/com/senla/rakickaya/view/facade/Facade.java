package com.senla.rakickaya.view.facade;

import java.text.ParseException;
import java.util.Map;

import com.senla.rakickaya.controller.api.services.ICoursesService;
import com.senla.rakickaya.controller.api.services.ILectorsService;
import com.senla.rakickaya.controller.api.services.IStudentsService;
import com.senla.rakickaya.controller.api.services.ITimeTableService;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.beans.Lesson;
import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.model.exception.EntityNotFoundException;
import com.senla.rakickaya.utils.launcher.Launcher;
import com.senla.rakickaya.view.api.facade.IFacade;
import com.senla.rakickaya.view.comparators.course.AlphabetCourseComparator;
import com.senla.rakickaya.view.comparators.course.CountStudentsComparator;
import com.senla.rakickaya.view.comparators.course.DateCourseComparator;
import com.senla.rakickaya.view.comparators.course.LectorNameComparator;
import com.senla.rakickaya.view.comparators.lector.AlphabetLectorComparator;
import com.senla.rakickaya.view.comparators.lesson.AlphabetLessonComparator;
import com.senla.rakickaya.view.comparators.lesson.DateLessonComparator;
import com.senla.rakickaya.view.dataExchange.Request;
import com.senla.rakickaya.view.dataExchange.RequestExtractor;
import com.senla.rakickaya.view.dataExchange.Response;

public class Facade implements IFacade {

	private static Facade facade;

	public static Facade getInstance() {
		if (facade == null) {
			Launcher launcher = Launcher.getInstance();
			facade = new Facade(launcher.getServiceCource(), launcher.getServiceLect(), launcher.getServiceStud(),
					launcher.getServiceTime());
		}
		return facade;
	}

	private ICoursesService mCoursesService;
	private ILectorsService mLectorsService;
	private IStudentsService mStudentsService;
	private ITimeTableService mTimeTableService;

	private Facade(ICoursesService mCoursesService, ILectorsService mLectorsService, IStudentsService mStudentsService,
			ITimeTableService mTimeTableService) {
		super();
		this.mCoursesService = mCoursesService;
		this.mLectorsService = mLectorsService;
		this.mStudentsService = mStudentsService;
		this.mTimeTableService = mTimeTableService;
	}

	@Override
	public Response getSortedCoursesByStartDate() {
		Response response = new Response("ok");
		for (Course course : mCoursesService.getSortedList(new DateCourseComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public Response getSortedCoursesByCountStudents() {
		Response response = new Response("ok");
		for (Course course : mCoursesService.getSortedList(new CountStudentsComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public Response getSortedCoursesByLectorName() {
		Response response = new Response("ok");
		for (Course course : mCoursesService.getSortedList(new LectorNameComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public Response getSortedCoursesByAlphabet() {
		Response response = new Response("ok");
		for (Course course : mCoursesService.getSortedList(new AlphabetCourseComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public Response getSortedLectorsByAlphabet() {
		Response response = new Response("ok");
		for (Lector lector : mLectorsService.getSortedList(new AlphabetLectorComparator())) {
			response.addNote(lector.toString());
		}
		return response;
	}

	@Override
	public Response getSortedLectorsByCountCourses() {
		Response response = new Response("ok");
		Map<Lector, Integer> map = mLectorsService.getLectorsInformation();
		for (Lector lector : map.keySet()) {
			response.addNote(lector.toString() + " " + map.get(lector));
		}
		return response;
	}

	@Override
	public Response getDetailedDescription(Request pIdCourse) {
		RequestExtractor requestExtractor = new RequestExtractor(pIdCourse);
		Response response = new Response("ok");
		Course course = mCoursesService.getCourse(requestExtractor.extractIdCourse());
		// if (course == null)
		// Printer.show("This course not exist");
		response.addNote(String.format("Detailed description:\ndescription=%s\nlector=%s\n", course.getDescription(),
				course.getLector().getName()));

		response.addNote("Students:\n");
		for (Student student : course.getStudents()) {
			if (student != null) {
				response.addNote(student.getNameStudent());
			}
		}
		return response;
	}

	@Override
	public Response getSortedTimeTableByDate() {
		Response response = new Response("ok");
		for (Lesson lesson : mTimeTableService.getSortedList(new DateLessonComparator())) {
			response.addNote(lesson.toString());
		}
		return response;
	}

	@Override
	public Response getSortedTimeTableByAlphabet() {
		Response response = new Response("ok");
		for (Lesson lesson : mTimeTableService.getSortedList(new AlphabetLessonComparator())) {
			response.addNote(lesson.toString());
		}
		return response;
	}

	@Override
	public Response getSortedCoursesByStartDate(Request afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new DateCourseComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;

	}

	@Override
	public Response getSortedCoursesByCountStudents(Request afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new CountStudentsComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getSortedCoursesByLectorName(Request afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new LectorNameComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getSortedCoursesByAlphabet(Request afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new AlphabetCourseComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getSortedCurrentCoursesByStartDate(Request currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
					new DateCourseComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getSortedCurrentCoursesByCountStudents(Request currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
					new CountStudentsComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getSortedCurrentCoursesByLectorName(Request currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
					new LectorNameComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getSortedCurrentCoursesByAlphabet(Request currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
					new AlphabetCourseComparator())) {
				response.addNote(course.toString());
			}

		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response getTotalCountCourse() {
		Response response = new Response("ok");
		response.addNote("Total count courses: " + mCoursesService.getTotalCountCourses());
		return response;
	}

	@Override
	public Response getTotalCountStudents() {
		Response response = new Response("ok");
		response.addNote("Total count students: " + mStudentsService.getTotalCountStudents());
		return response;
	}

	@Override
	public Response getTotalCountLectors() {
		Response response = new Response("ok");
		response.addNote("Total count lectors: " + mLectorsService.getTotalCountLectors());
		return response;
	}

	@Override
	public Response getListLessonByDate(Request date) {
		RequestExtractor requestExtractor = new RequestExtractor(date);
		Response response = new Response("ok");
		try {
			for (Lesson lesson : mTimeTableService.getListLessons(requestExtractor.extractDate())) {
				response.addNote(lesson.toString());
			}
		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public Response getPastCourses(Request interval) {
		RequestExtractor requestExtractor = new RequestExtractor(interval);
		Response response = new Response("ok");
		try {
			for (Course course : mCoursesService.getPastCourses(requestExtractor.extractStartDate(), requestExtractor.extractEndDate())) {
				response.addNote(course.toString());
			}
		} catch (ParseException e) {
			response.setStatusCode("error");
			response.addNote("Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response addCourse(Request course) {
		RequestExtractor requestExtractor = new RequestExtractor(course);
		Response response = null;
		try {
			mCoursesService.addCourse(requestExtractor.extractCourse());
			response = new Response("ok");
			response.addNote("The course was added successfully");
		} catch (ParseException e) {
			response = new Response("error");
			response.addNote("Incorrect date format");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response removeCourse(Request idCourse) {
		RequestExtractor requestExtractor = new RequestExtractor(idCourse);
		Response response = null;
		try {
			mCoursesService.removeCourse(requestExtractor.extractIdCourse());
			response = new Response("ok");
			response.addNote("The course was removed successfully");
		} catch (Exception e) {
			response = new Response("error");
			response.addNote("Course not found");
		}
		return response;
	}

	@Override
	public Response addLectureToCourse(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = new Response("ok");
		mCoursesService.addLectureToCourse(requestExtractor.extractLecture(), requestExtractor.extractIdCourse());
		response.addNote("The lecture was added to the course successfully");
		return response;
	}

	@Override
	public Response removeLectureFromCourse(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = null;
		try {
			mCoursesService.removeLectureFromCourse(requestExtractor.extractIdLecture(), requestExtractor.extractIdCourse());
			response = new Response("ok");
			response.addNote("The lecture was removed from the course successfully");
		} catch (EntityNotFoundException e) {
			response = new Response("error");
			response.addNote("Course or lecture not found");
			e.printStackTrace();
		}
		return response;
	
	}

	@Override
	public Response addStudent(Request student) {
		RequestExtractor requestExtractor = new RequestExtractor(student);
		Response response = new Response("ok");
		mStudentsService.addStudent(requestExtractor.extractStudent());
		response.addNote("The student was added  successfully");
		return response;
	}

	@Override
	public Response addStudentToCourse(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = new Response("ok");
		mCoursesService.addStudentToCourse(requestExtractor.extractIdStudent(), requestExtractor.extractIdCourse());
		response.addNote("The student was added to the course successfully");
		return response;
	}

	@Override
	public Response removeStudent(Request idStudent) {
		RequestExtractor requestExtractor = new RequestExtractor(idStudent);
		Response response = null;
		try {
			mStudentsService.removeStudent(requestExtractor.extractIdStudent());
			response = new Response("ok");
			response.addNote("The student was removed successfully");
		} catch (EntityNotFoundException e) {
			response = new Response("error");
			response.addNote("Student not found");
			e.printStackTrace();
		}
		return response;
	
	}

	@Override
	public Response removeStudentFromCourse(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = null;
		try {
			mCoursesService.removeStudentFromCourse(requestExtractor.extractIdStudent(), requestExtractor.extractIdCourse());
			response = new Response("ok");
			response.addNote("The student was removed from the course successfully");
		} catch (EntityNotFoundException e) {
			response = new Response("error");
			response.addNote("Course or student not found");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response addLector(Request lector) {
		RequestExtractor requestExtractor = new RequestExtractor(lector);
		Response response = new Response("ok");
		mLectorsService.addLector(requestExtractor.extractLector());
		response.addNote("The lector was added  successfully");
		return response;
	}

	@Override
	public Response addLectorToCourse(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = new Response("ok");
		mCoursesService.addLectorToCourse(requestExtractor.extractIdLector(), requestExtractor.extractIdCourse());
		response.addNote("The lector was added to the course successfully");
		return response;
	}

	@Override
	public Response removeLector(Request idLector) {
		RequestExtractor requestExtractor = new RequestExtractor(idLector);
		Response response = null;
		try {
			mLectorsService.removeLector(requestExtractor.extractIdLector());
			response = new Response("ok");
			response.addNote("The lector was removed successfully");
		} catch (EntityNotFoundException e) {
			response = new Response("error");
			response.addNote("Lector not found");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response removeLectorFromCourse(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = null;
		try {
			mCoursesService.removeLectorFromCourse(requestExtractor.extractIdLector(), requestExtractor.extractIdCourse());
			response = new Response("ok");
			response.addNote("The lector was removed from the course successfully");
		} catch (EntityNotFoundException e) {
			response = new Response("error");
			response.addNote("Course or lector not found");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response createTimeTableForLecture(Request request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		Response response = null;
		try {
			mTimeTableService.createLesson(requestExtractor.extractIdLecture(), requestExtractor.extractDate());
			response = new Response("ok");
			response.addNote("The time table was created successfully");
		} catch (ParseException e) {
			response = new Response("error");
			response.addNote("Incorrect date format");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Response removeTimeTableForLecture(Request idLecture) {
		RequestExtractor requestExtractor = new RequestExtractor(idLecture);
		Response response = null;
		try {
			mTimeTableService.removeLessonByLecture(requestExtractor.extractIdLecture());
			response = new Response("ok");
			response.addNote("The lesson was removed from the table successfully");
		} catch (EntityNotFoundException e) {
			response = new Response("error");
			response.addNote("Lesson not found");
			e.printStackTrace();
		}
		return response;
	}

}
