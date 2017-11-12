package com.senla.rakickaya.view.facade;

import java.text.ParseException;
import java.util.Map;

import com.senla.rakickaya.controller.services.CoursesService;
import com.senla.rakickaya.controller.services.LectorsService;
import com.senla.rakickaya.controller.services.StudentsService;
import com.senla.rakickaya.controller.services.TimeTableService;
import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.api.services.ICoursesService;
import com.senla.rakickaya.courseplanner.api.services.ILectorsService;
import com.senla.rakickaya.courseplanner.api.services.IStudentsService;
import com.senla.rakickaya.courseplanner.api.services.ITimeTableService;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.view.comparators.course.AlphabetCourseComparator;
import com.senla.rakickaya.view.comparators.course.CountStudentsComparator;
import com.senla.rakickaya.view.comparators.course.DateCourseComparator;
import com.senla.rakickaya.view.comparators.course.LectorNameComparator;
import com.senla.rakickaya.view.comparators.lector.AlphabetLectorComparator;
import com.senla.rakickaya.view.comparators.lesson.AlphabetLessonComparator;
import com.senla.rakickaya.view.comparators.lesson.DateLessonComparator;
import com.senla.rakickaya.view.dataExchange.RequestExtractor;
import com.senla.rakickaya.view.dataExchange.Response;

public class Facade implements IFacade {

	private static Facade facade;

	public static Facade getInstance() {
		if (facade == null) {

			facade = new Facade(new CoursesService(), new LectorsService(), new StudentsService(),
					new TimeTableService());
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
	public IResponse getSortedCoursesByStartDate() {
		IResponse response = new Response("ok");
		for (ICourse course : mCoursesService.getSortedList(new DateCourseComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedCoursesByCountStudents() {
		IResponse response = new Response("ok");
		for (ICourse course : mCoursesService.getSortedList(new CountStudentsComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedCoursesByLectorName() {
		IResponse response = new Response("ok");
		for (ICourse course : mCoursesService.getSortedList(new LectorNameComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedCoursesByAlphabet() {
		IResponse response = new Response("ok");
		for (ICourse course : mCoursesService.getSortedList(new AlphabetCourseComparator())) {
			response.addNote(course.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedLectorsByAlphabet() {
		IResponse response = new Response("ok");
		for (ILector lector : mLectorsService.getSortedList(new AlphabetLectorComparator())) {
			response.addNote(lector.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedLectorsByCountCourses() {
		IResponse response = new Response("ok");
		Map<ILector, Integer> map = mLectorsService.getLectorsInformation();
		for (ILector lector : map.keySet()) {
			response.addNote(lector.toString() + " " + map.get(lector));
		}
		return response;
	}

	@Override
	public IResponse getDetailedDescription(IRequest pIdCourse) {
		RequestExtractor requestExtractor = new RequestExtractor(pIdCourse);
		IResponse response = new Response("ok");
		ICourse course = mCoursesService.getCourse(requestExtractor.extractIdCourse());
		// if (course == null)
		// Printer.show("This course not exist");
		response.addNote(String.format("Detailed description:\ndescription=%s\nlector=%s\n", course.getDescription(),
				course.getLector().getName()));

		response.addNote("Students:\n");
		for (IStudent student : course.getStudents()) {
			if (student != null) {
				response.addNote(student.getNameStudent());
			}
		}
		return response;
	}

	@Override
	public IResponse getSortedTimeTableByDate() {
		IResponse response = new Response("ok");
		for (ILesson lesson : mTimeTableService.getSortedList(new DateLessonComparator())) {
			response.addNote(lesson.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedTimeTableByAlphabet() {
		IResponse response = new Response("ok");
		for (ILesson lesson : mTimeTableService.getSortedList(new AlphabetLessonComparator())) {
			response.addNote(lesson.toString());
		}
		return response;
	}

	@Override
	public IResponse getSortedCoursesByStartDate(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
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
	public IResponse getSortedCoursesByCountStudents(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
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
	public IResponse getSortedCoursesByLectorName(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
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
	public IResponse getSortedCoursesByAlphabet(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
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
	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
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
	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
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
	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
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
	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
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
	public IResponse getTotalCountCourse() {
		Response response = new Response("ok");
		response.addNote("Total count courses: " + mCoursesService.getTotalCountCourses());
		return response;
	}

	@Override
	public IResponse getTotalCountStudents() {
		Response response = new Response("ok");
		response.addNote("Total count students: " + mStudentsService.getTotalCountStudents());
		return response;
	}

	@Override
	public IResponse getTotalCountLectors() {
		Response response = new Response("ok");
		response.addNote("Total count lectors: " + mLectorsService.getTotalCountLectors());
		return response;
	}

	@Override
	public IResponse getListLessonByDate(IRequest date) {
		RequestExtractor requestExtractor = new RequestExtractor(date);
		IResponse response = new Response("ok");
		try {
			for (ILesson lesson : mTimeTableService.getListLessons(requestExtractor.extractDate())) {
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
	public IResponse getPastCourses(IRequest interval) {
		RequestExtractor requestExtractor = new RequestExtractor(interval);
		IResponse response = new Response("ok");
		try {
			for (ICourse course : mCoursesService.getPastCourses(requestExtractor.extractStartDate(),
					requestExtractor.extractEndDate())) {
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
	public IResponse addCourse(IRequest course) {
		RequestExtractor requestExtractor = new RequestExtractor(course);
		IResponse response = null;
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
	public IResponse removeCourse(IRequest idCourse) {
		RequestExtractor requestExtractor = new RequestExtractor(idCourse);
		IResponse response = null;
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
	public IResponse addLectureToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = new Response("ok");
		mCoursesService.addLectureToCourse(requestExtractor.extractLecture(), requestExtractor.extractIdCourse());
		response.addNote("The lecture was added to the course successfully");
		return response;
	}

	@Override
	public IResponse removeLectureFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = null;
		try {
			mCoursesService.removeLectureFromCourse(requestExtractor.extractIdLecture(),
					requestExtractor.extractIdCourse());
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
	public IResponse addStudent(IRequest student) {
		RequestExtractor requestExtractor = new RequestExtractor(student);
		IResponse response = new Response("ok");
		mStudentsService.addStudent(requestExtractor.extractStudent());
		response.addNote("The student was added  successfully");
		return response;
	}

	@Override
	public IResponse addStudentToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = new Response("ok");
		mCoursesService.addStudentToCourse(requestExtractor.extractIdStudent(), requestExtractor.extractIdCourse());
		response.addNote("The student was added to the course successfully");
		return response;
	}

	@Override
	public IResponse removeStudent(IRequest idStudent) {
		RequestExtractor requestExtractor = new RequestExtractor(idStudent);
		IResponse response = null;
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
	public IResponse removeStudentFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = null;
		try {
			mCoursesService.removeStudentFromCourse(requestExtractor.extractIdStudent(),
					requestExtractor.extractIdCourse());
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
	public IResponse addLector(IRequest lector) {
		RequestExtractor requestExtractor = new RequestExtractor(lector);
		IResponse response = new Response("ok");
		mLectorsService.addLector(requestExtractor.extractLector());
		response.addNote("The lector was added  successfully");
		return response;
	}

	@Override
	public IResponse addLectorToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = new Response("ok");
		mCoursesService.addLectorToCourse(requestExtractor.extractIdLector(), requestExtractor.extractIdCourse());
		response.addNote("The lector was added to the course successfully");
		return response;
	}

	@Override
	public IResponse removeLector(IRequest idLector) {
		RequestExtractor requestExtractor = new RequestExtractor(idLector);
		IResponse response = null;
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
	public IResponse removeLectorFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = null;
		try {
			mCoursesService.removeLectorFromCourse(requestExtractor.extractIdLector(),
					requestExtractor.extractIdCourse());
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
	public IResponse createTimeTableForLecture(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = null;
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
	public IResponse removeTimeTableForLecture(IRequest idLecture) {
		RequestExtractor requestExtractor = new RequestExtractor(idLecture);
		IResponse response = null;
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
