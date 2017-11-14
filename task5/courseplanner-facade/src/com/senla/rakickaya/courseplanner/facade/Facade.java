package com.senla.rakickaya.courseplanner.facade;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.api.services.ICoursesService;
import com.senla.rakickaya.courseplanner.api.services.ILectorsService;
import com.senla.rakickaya.courseplanner.api.services.IStudentsService;
import com.senla.rakickaya.courseplanner.api.services.ITimeTableService;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.facade.comparators.course.AlphabetCourseComparator;
import com.senla.rakickaya.courseplanner.facade.comparators.course.CountStudentsComparator;
import com.senla.rakickaya.courseplanner.facade.comparators.course.DateCourseComparator;
import com.senla.rakickaya.courseplanner.facade.comparators.course.LectorNameComparator;
import com.senla.rakickaya.courseplanner.facade.comparators.lector.AlphabetLectorComparator;
import com.senla.rakickaya.courseplanner.facade.comparators.lesson.AlphabetLessonComparator;
import com.senla.rakickaya.courseplanner.facade.comparators.lesson.DateLessonComparator;
import com.senla.rakickaya.courseplanner.facade.dataExchange.RequestExtractor;
import com.senla.rakickaya.courseplanner.facade.dataExchange.Response;
import com.senla.rakickaya.courseplanner.services.CoursesService;
import com.senla.rakickaya.courseplanner.services.LectorsService;
import com.senla.rakickaya.courseplanner.services.StudentsService;
import com.senla.rakickaya.courseplanner.services.TimeTableService;

public class Facade implements IFacade {

	private static Facade facade;

	private Logger logger = Logger.getLogger(Facade.class.getName());

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
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getSortedList(new DateCourseComparator()));
		return response;
	}

	@Override
	public IResponse getSortedCoursesByCountStudents() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getSortedList(new CountStudentsComparator()));
		return response;
	}

	@Override
	public IResponse getSortedCoursesByLectorName() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getSortedList(new LectorNameComparator()));
		return response;
	}

	@Override
	public IResponse getSortedCoursesByAlphabet() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getSortedList(new AlphabetCourseComparator()));
		return response;
	}

	@Override
	public IResponse getSortedLectorsByAlphabet() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mLectorsService.getSortedList(new AlphabetLectorComparator()));
		return response;
	}

	@Override
	public IResponse getSortedLectorsByCountCourses() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mLectorsService.getLectorsInformation());
		return response;
	}

	@Override
	public IResponse getDetailedDescription(IRequest pIdCourse) {
		RequestExtractor requestExtractor = new RequestExtractor(pIdCourse);
		IResponse response = new Response();
		ICourse course = mCoursesService.getCourse(requestExtractor.extractIdCourse());
		response.addHead(TagsResponse.MESSAGE, String.format("Detailed description:\ndescription=%s\nlector=%s\n",
				course.getDescription(), course.getLector().getName()));
		response.addHead(TagsResponse.DATA, course.getStudents());
		return response;
	}

	@Override
	public IResponse getSortedTimeTableByDate() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mTimeTableService.getSortedList(new DateLessonComparator()));
		return response;
	}

	@Override
	public IResponse getSortedTimeTableByAlphabet() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mTimeTableService.getSortedList(new AlphabetLessonComparator()));
		return response;
	}

	@Override
	public IResponse getSortedCoursesByStartDate(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new DateCourseComparator()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;

	}

	@Override
	public IResponse getSortedCoursesByCountStudents(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new CountStudentsComparator()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getSortedCoursesByLectorName(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new LectorNameComparator()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getSortedCoursesByAlphabet(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mCoursesService.getListCoursesAfterDate(requestExtractor.extractDate(),
					new AlphabetCourseComparator()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA,
					mCoursesService.getListCurrentCourses(requestExtractor.extractDate(), new DateCourseComparator()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response();
		try {

			response.addHead(TagsResponse.DATA, mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
					new CountStudentsComparator()));

		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA,
					mCoursesService.getListCurrentCourses(requestExtractor.extractDate(), new LectorNameComparator()));

		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mCoursesService.getListCurrentCourses(requestExtractor.extractDate(),
					new AlphabetCourseComparator()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getTotalCountCourse() {
		Response response = new Response();
		response.addHead(TagsResponse.TOTAL_COUNT, mCoursesService.getTotalCountCourses());
		return response;
	}

	@Override
	public IResponse getTotalCountStudents() {
		Response response = new Response();
		response.addHead(TagsResponse.TOTAL_COUNT, mStudentsService.getTotalCountStudents());
		return response;
	}

	@Override
	public IResponse getTotalCountLectors() {
		Response response = new Response();
		response.addHead(TagsResponse.TOTAL_COUNT, mLectorsService.getTotalCountLectors());
		return response;
	}

	@Override
	public IResponse getListLessonByDate(IRequest date) {
		RequestExtractor requestExtractor = new RequestExtractor(date);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mTimeTableService.getListLessons(requestExtractor.extractDate()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public IResponse getPastCourses(IRequest interval) {
		RequestExtractor requestExtractor = new RequestExtractor(interval);
		IResponse response = new Response();
		try {
			response.addHead(TagsResponse.DATA, mCoursesService.getPastCourses(requestExtractor.extractStartDate(),
					requestExtractor.extractEndDate()));
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response.addHead(TagsResponse.MESSAGE, "Bad request(Incorrect date format)");
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
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The course was added successfully");
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Incorrect date format");
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
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The course was removed successfully");
		} catch (Exception e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Course not found");
		}
		return response;
	}

	@Override
	public IResponse addLectureToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = new Response();
		mCoursesService.addLectureToCourse(requestExtractor.extractLecture(), requestExtractor.extractIdCourse());
		response.addHead(TagsResponse.MESSAGE, "The lecture was added to the course successfully");
		return response;
	}

	@Override
	public IResponse removeLectureFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = null;
		try {
			mCoursesService.removeLectureFromCourse(requestExtractor.extractIdLecture(),
					requestExtractor.extractIdCourse());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The lecture was removed from the course successfully");
		} catch (EntityNotFoundException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Course or lecture not found");
			e.printStackTrace();
		}
		return response;

	}

	@Override
	public IResponse addStudent(IRequest student) {
		RequestExtractor requestExtractor = new RequestExtractor(student);
		IResponse response = new Response();
		mStudentsService.addStudent(requestExtractor.extractStudent());
		response.addHead(TagsResponse.MESSAGE, "The student was added  successfully");
		return response;
	}

	@Override
	public IResponse addStudentToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = new Response();
		mCoursesService.addStudentToCourse(requestExtractor.extractIdStudent(), requestExtractor.extractIdCourse());
		response.addHead(TagsResponse.MESSAGE, "The student was added to the course successfully");
		return response;
	}

	@Override
	public IResponse removeStudent(IRequest idStudent) {
		RequestExtractor requestExtractor = new RequestExtractor(idStudent);
		IResponse response = null;
		try {
			mStudentsService.removeStudent(requestExtractor.extractIdStudent());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The student was removed successfully");
		} catch (EntityNotFoundException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Student not found");
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
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The student was removed from the course successfully");
		} catch (EntityNotFoundException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Course or student not found");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse addLector(IRequest lector) {
		RequestExtractor requestExtractor = new RequestExtractor(lector);
		IResponse response = new Response();
		mLectorsService.addLector(requestExtractor.extractLector());
		response.addHead(TagsResponse.MESSAGE, "The lector was added  successfully");
		return response;
	}

	@Override
	public IResponse addLectorToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		IResponse response = new Response();
		mCoursesService.addLectorToCourse(requestExtractor.extractIdLector(), requestExtractor.extractIdCourse());
		response.addHead(TagsResponse.MESSAGE, "The lector was added to the course successfully");
		return response;
	}

	@Override
	public IResponse removeLector(IRequest idLector) {
		RequestExtractor requestExtractor = new RequestExtractor(idLector);
		IResponse response = null;
		try {
			mLectorsService.removeLector(requestExtractor.extractIdLector());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The lector was removed successfully");
		} catch (EntityNotFoundException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Lector not found");
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
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The lector was removed from the course successfully");
		} catch (EntityNotFoundException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Course or lector not found");
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
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The time table was created successfully");
		} catch (ParseException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Incorrect date format");
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
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "The lesson was removed from the table successfully");
		} catch (EntityNotFoundException e) {
			logger.info(new Date() + " " + e.getMessage());
			response = new Response();
			response.addHead(TagsResponse.MESSAGE, "Lesson not found");
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public IResponse getAllStudents() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mStudentsService.getListStudents());
		return response;
	}

	@Override
	public IResponse getAllLectors() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mLectorsService.getListLectors());
		return response;
	}

	@Override
	public IResponse getAllCourses() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getListCourses());
		return response;
	}

	@Override
	public IResponse getAllLectures() {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getAllLectures());
		return response;
	}

	@Override
	public IResponse getLecturesByCourse(int n) {
		IResponse response = new Response();
		response.addHead(TagsResponse.DATA, mCoursesService.getListCourses().get(n).getLectures());
		return response;
	}

	@Override
	public void save() {
		mCoursesService.save();
		mLectorsService.save();
		mStudentsService.save();
		mTimeTableService.save();

	}

}
