package com.senla.rakickaya.courseplanner.facade;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
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
import com.senla.rakickaya.courseplanner.facade.dataExchange.ResponseBuilder;
import com.senla.rakickaya.courseplanner.services.CoursesService;
import com.senla.rakickaya.courseplanner.services.LectorsService;
import com.senla.rakickaya.courseplanner.services.StudentsService;
import com.senla.rakickaya.courseplanner.services.TimeTableService;

public class Facade implements IFacade {

	private static final String OK = "OK";

	private static final String CLONED_BAD = "Course wasn't cloned";

	private static final String CLONED_SUCCESSFULLY = "The course was cloned successfully";

	private static final String LESSON_NOT_FOUND = "Lesson not found";

	private static final String COURSE_OR_LECTOR_NOT_FOUND = "Course or lector not found";

	private static final String LECTOR_NOT_FOUND = "Lector not found";

	private static final String COURSE_OR_STUDENT_NOT_FOUND = "Course or student not found";

	private static final String STUDENT_NOT_FOUND = "Student not found";

	private static final String COURSE_OR_LECTURE_NOT_FOUND = "Course or lecture not found";

	private static final String COURSE_NOT_FOUND = "Course not found";

	private static final String REMOVED_SUCCESSFULLY = "The course was removed successfully";

	private static final String ADDED_SUCCESSFULLY = "The course was added successfully";

	private static final String BAD_REQUEST = "Bad request(Incorrect date format)";

	private static final String LIST_IS_EMPTY = "List is Empty";

	private static Facade facade;

	private static final Logger logger = Logger.getLogger(Facade.class.getName());

	private ICoursesService mCoursesService;
	private ILectorsService mLectorsService;
	private IStudentsService mStudentsService;
	private ITimeTableService mTimeTableService;

	public static Facade getInstance() {
		if (facade == null) {

			facade = new Facade(new CoursesService(), new LectorsService(), new StudentsService(),
					new TimeTableService());
		}
		return facade;
	}

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
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new DateCourseComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByCountStudents() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new CountStudentsComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByLectorName() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new LectorNameComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByAlphabet() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new AlphabetCourseComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedLectorsByAlphabet() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getSortedList(new AlphabetLectorComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedLectorsByCountCourses() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getLectorsInformation(), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getDetailedDescription(IRequest pIdCourse) {

		RequestExtractor requestExtractor = new RequestExtractor(pIdCourse);
		ICourse course = mCoursesService.getCourse(requestExtractor.extractIdCourse());
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(course.getStudents(),
				String.format("Detailed description:\ndescription=%s\nlector=%s\n", course.getDescription(),
						course.getLector().getName()),
				LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedTimeTableByDate() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mTimeTableService.getSortedList(new DateLessonComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedTimeTableByAlphabet() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mTimeTableService.getSortedList(new AlphabetLessonComparator()), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByStartDate(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new DateCourseComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());

		}
		return responseBuilder.build(BAD_REQUEST);

	}

	@Override
	public IResponse getSortedCoursesByCountStudents(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new CountStudentsComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCoursesByLectorName(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new LectorNameComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCoursesByAlphabet(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new AlphabetCourseComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new DateCourseComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new CountStudentsComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new LectorNameComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new AlphabetCourseComparator()),
					LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getTotalCountCourse() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getTotalCountCourses());
	}

	@Override
	public IResponse getTotalCountStudents() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mStudentsService.getTotalCountStudents());
	}

	@Override
	public IResponse getTotalCountLectors() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getTotalCountLectors());
	}

	@Override
	public IResponse getLessonsByDate(IRequest date) {
		RequestExtractor requestExtractor = new RequestExtractor(date);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(mTimeTableService.getLessons(requestExtractor.extractDate()), LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse getPastCourses(IRequest interval) {
		RequestExtractor requestExtractor = new RequestExtractor(interval);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(mCoursesService.getPastCourses(requestExtractor.extractStartDate(),
					requestExtractor.extractEndDate()), LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse addCourse(IRequest course) {
		RequestExtractor requestExtractor = new RequestExtractor(course);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {

			mCoursesService.addCourse(requestExtractor.extractCourse());
			return responseBuilder.build(ADDED_SUCCESSFULLY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(BAD_REQUEST);
	}

	@Override
	public IResponse removeCourse(IRequest idCourse) {

		RequestExtractor requestExtractor = new RequestExtractor(idCourse);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeCourse(requestExtractor.extractIdCourse());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (Exception e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(COURSE_NOT_FOUND);
	}

	@Override
	public IResponse addLectureToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mCoursesService.addLectureToCourse(requestExtractor.extractLecture(), requestExtractor.extractIdCourse());
		return responseBuilder.build(ADDED_SUCCESSFULLY);

	}

	@Override
	public IResponse removeLectureFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeLectureFromCourse(requestExtractor.extractIdLecture(),
					requestExtractor.extractIdCourse());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(COURSE_OR_LECTURE_NOT_FOUND);

	}

	@Override
	public IResponse addStudent(IRequest student) {
		RequestExtractor requestExtractor = new RequestExtractor(student);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mStudentsService.addStudent(requestExtractor.extractStudent());
		return responseBuilder.build(ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse addStudentToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mCoursesService.addStudentToCourse(requestExtractor.extractIdStudent(), requestExtractor.extractIdCourse());
		return responseBuilder.build(ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse removeStudent(IRequest idStudent) {
		RequestExtractor requestExtractor = new RequestExtractor(idStudent);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mStudentsService.removeStudent(requestExtractor.extractIdStudent());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(STUDENT_NOT_FOUND);

	}

	@Override
	public IResponse removeStudentFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeStudentFromCourse(requestExtractor.extractIdStudent(),
					requestExtractor.extractIdCourse());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(COURSE_OR_STUDENT_NOT_FOUND);
	}

	@Override
	public IResponse addLector(IRequest lector) {
		RequestExtractor requestExtractor = new RequestExtractor(lector);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mLectorsService.addLector(requestExtractor.extractLector());
		return responseBuilder.build(ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse addLectorToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mCoursesService.addLectorToCourse(requestExtractor.extractIdLector(), requestExtractor.extractIdCourse());
		return responseBuilder.build(ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse removeLector(IRequest idLector) {
		RequestExtractor requestExtractor = new RequestExtractor(idLector);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mLectorsService.removeLector(requestExtractor.extractIdLector());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(LECTOR_NOT_FOUND);
	}

	@Override
	public IResponse removeLectorFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeLectorFromCourse(requestExtractor.extractIdLector(),
					requestExtractor.extractIdCourse());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(COURSE_OR_LECTOR_NOT_FOUND);
	}

	@Override
	public IResponse createTimeTableForLecture(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		String message;
		try {
			mTimeTableService.createLesson(requestExtractor.extractIdLecture(), requestExtractor.extractDate(),
					requestExtractor.extractCountStudent());
			return responseBuilder.build(ADDED_SUCCESSFULLY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
			message = BAD_REQUEST;
		} catch (Exception e) {
			logger.error(new Date() + " " + e.getMessage());
			message = e.getMessage();
		}
		return responseBuilder.build(message);
	}

	@Override
	public IResponse removeTimeTableForLecture(IRequest idLecture) {
		RequestExtractor requestExtractor = new RequestExtractor(idLecture);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mTimeTableService.removeLessonByLecture(requestExtractor.extractIdLecture());
			return responseBuilder.build(REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(LESSON_NOT_FOUND);
	}

	@Override
	public IResponse getAllStudents() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mStudentsService.getStudents(), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getAllLectors() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getLectors(), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getAllCourses() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getCourses(), LIST_IS_EMPTY);
	}

	@Override
	public IResponse getAllLectures() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getAllLectures(), LIST_IS_EMPTY);
	}

	@Override
	public IResponse cloneCourse(IRequest idCourse) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			RequestExtractor requestExtractor = new RequestExtractor(idCourse);
			mCoursesService.cloneCourseById(requestExtractor.extractIdCourse());
			return responseBuilder.build(CLONED_SUCCESSFULLY);
		} catch (CloneNotSupportedException | EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(CLONED_BAD);
	}

	@Override
	public IResponse exportCourse(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mCoursesService.exportCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse exportLector(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mLectorsService.exportCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse exportStudent(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mStudentsService.exportCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse exportTimeTable(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mTimeTableService.exportCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse importCourse(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mCoursesService.importCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse importLector(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mLectorsService.importCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse importStudent(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mStudentsService.importCSV(path);
		return responseBuilder.build(OK);
	}

	@Override
	public IResponse importTimeTable(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mTimeTableService.importCSV(path);
		return responseBuilder.build(OK);
	}

}
