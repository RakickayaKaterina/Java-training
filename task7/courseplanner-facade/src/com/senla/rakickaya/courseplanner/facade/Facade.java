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
import com.senla.rakickaya.courseplanner.di.ServiceDI;
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

public class Facade implements IFacade {

	private static final Logger logger = Logger.getLogger(Facade.class.getName());

	private ICoursesService mCoursesService;
	private ILectorsService mLectorsService;
	private IStudentsService mStudentsService;
	private ITimeTableService mTimeTableService;

	public Facade() {
		super();
		mCoursesService = (ICoursesService) ServiceDI.getInstance().getObject(ICoursesService.class);
		mLectorsService = (ILectorsService) ServiceDI.getInstance().getObject(ILectorsService.class);
		mStudentsService = (IStudentsService) ServiceDI.getInstance().getObject(IStudentsService.class);
		mTimeTableService = (ITimeTableService) ServiceDI.getInstance().getObject(ITimeTableService.class);
	}

	@Override
	public IResponse getSortedCoursesByStartDate() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new DateCourseComparator()), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByCountStudents() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new CountStudentsComparator()),
				Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByLectorName() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new LectorNameComparator()), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByAlphabet() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getSortedList(new AlphabetCourseComparator()),
				Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedLectorsByAlphabet() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getSortedList(new AlphabetLectorComparator()),
				Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedLectorsByCountCourses() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getLectorsInformation(), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getDetailedDescription(IRequest pIdCourse) {

		RequestExtractor requestExtractor = new RequestExtractor(pIdCourse);
		ICourse course = mCoursesService.getCourse(requestExtractor.extractIdCourse());
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(course.getStudents(),
				String.format("Detailed description:\ndescription=%s\nlector=%s\n", course.getDescription(),
						course.getLector().getName()),
				Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedTimeTableByDate() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mTimeTableService.getSortedList(new DateLessonComparator()),
				Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedTimeTableByAlphabet() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mTimeTableService.getSortedList(new AlphabetLessonComparator()),
				Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getSortedCoursesByStartDate(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new DateCourseComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());

		}
		return responseBuilder.build(Messages.BAD_REQUEST);

	}

	@Override
	public IResponse getSortedCoursesByCountStudents(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new CountStudentsComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCoursesByLectorName(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new LectorNameComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCoursesByAlphabet(IRequest afterDate) {
		RequestExtractor requestExtractor = new RequestExtractor(afterDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCoursesAfterDate(requestExtractor.extractDate(), new AlphabetCourseComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new DateCourseComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new CountStudentsComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new LectorNameComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate) {
		RequestExtractor requestExtractor = new RequestExtractor(currentDate);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(
					mCoursesService.getCurrentCourses(requestExtractor.extractDate(), new AlphabetCourseComparator()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
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
			return responseBuilder.build(mTimeTableService.getLessons(requestExtractor.extractDate()),
					Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse getPastCourses(IRequest interval) {
		RequestExtractor requestExtractor = new RequestExtractor(interval);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			return responseBuilder.build(mCoursesService.getPastCourses(requestExtractor.extractStartDate(),
					requestExtractor.extractEndDate()), Messages.LIST_IS_EMPTY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse addCourse(IRequest course) {
		RequestExtractor requestExtractor = new RequestExtractor(course);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {

			mCoursesService.addCourse(requestExtractor.extractCourse());
			return responseBuilder.build(Messages.COURSE_ADDED_SUCCESSFULLY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.BAD_REQUEST);
	}

	@Override
	public IResponse removeCourse(IRequest idCourse) {

		RequestExtractor requestExtractor = new RequestExtractor(idCourse);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeCourse(requestExtractor.extractIdCourse());
			return responseBuilder.build(Messages.COURSE_REMOVED_SUCCESSFULLY);
		} catch (Exception e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.COURSE_NOT_FOUND);
	}

	@Override
	public IResponse addLectureToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mCoursesService.addLectureToCourse(requestExtractor.extractLecture(), requestExtractor.extractIdCourse());
		return responseBuilder.build(Messages.LECTURE_ADDED_SUCCESSFULLY);

	}

	@Override
	public IResponse removeLectureFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeLectureFromCourse(requestExtractor.extractIdLecture(),
					requestExtractor.extractIdCourse());
			return responseBuilder.build(Messages.LECTURE_REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.COURSE_OR_LECTURE_NOT_FOUND);

	}

	@Override
	public IResponse addStudent(IRequest student) {
		RequestExtractor requestExtractor = new RequestExtractor(student);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mStudentsService.addStudent(requestExtractor.extractStudent());
		return responseBuilder.build(Messages.STUDENT_ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse addStudentToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mCoursesService.addStudentToCourse(requestExtractor.extractIdStudent(), requestExtractor.extractIdCourse());
		return responseBuilder.build(Messages.STUDENT_ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse removeStudent(IRequest idStudent) {
		RequestExtractor requestExtractor = new RequestExtractor(idStudent);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mStudentsService.removeStudent(requestExtractor.extractIdStudent());
			return responseBuilder.build(Messages.STUDENT_REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.STUDENT_NOT_FOUND);

	}

	@Override
	public IResponse removeStudentFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeStudentFromCourse(requestExtractor.extractIdStudent(),
					requestExtractor.extractIdCourse());
			return responseBuilder.build(Messages.STUDENT_REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.COURSE_OR_STUDENT_NOT_FOUND);
	}

	@Override
	public IResponse addLector(IRequest lector) {
		RequestExtractor requestExtractor = new RequestExtractor(lector);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mLectorsService.addLector(requestExtractor.extractLector());
		return responseBuilder.build(Messages.LECTOR_ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse addLectorToCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		mCoursesService.addLectorToCourse(requestExtractor.extractIdLector(), requestExtractor.extractIdCourse());
		return responseBuilder.build(Messages.LECTOR_ADDED_SUCCESSFULLY);
	}

	@Override
	public IResponse removeLector(IRequest idLector) {
		RequestExtractor requestExtractor = new RequestExtractor(idLector);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mLectorsService.removeLector(requestExtractor.extractIdLector());
			return responseBuilder.build(Messages.LECTOR_REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.LECTOR_NOT_FOUND);
	}

	@Override
	public IResponse removeLectorFromCourse(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			mCoursesService.removeLectorFromCourse(requestExtractor.extractIdLector(),
					requestExtractor.extractIdCourse());
			return responseBuilder.build(Messages.LECTOR_REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.COURSE_OR_LECTOR_NOT_FOUND);
	}

	@Override
	public IResponse createTimeTableForLecture(IRequest request) {
		RequestExtractor requestExtractor = new RequestExtractor(request);
		ResponseBuilder responseBuilder = new ResponseBuilder();
		String message;
		try {
			mTimeTableService.createLesson(requestExtractor.extractIdLecture(), requestExtractor.extractDate(),
					requestExtractor.extractCountStudent());
			return responseBuilder.build(Messages.LESSON_ADDED_SUCCESSFULLY);
		} catch (ParseException e) {
			logger.error(new Date() + " " + e.getMessage());
			message = Messages.BAD_REQUEST;
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
			return responseBuilder.build(Messages.LESSON_REMOVED_SUCCESSFULLY);
		} catch (EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.LESSON_NOT_FOUND);
	}

	@Override
	public IResponse getAllStudents() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mStudentsService.getStudents(), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getAllLectors() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mLectorsService.getLectors(), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getAllCourses() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getCourses(), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse getAllLectures() {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		return responseBuilder.build(mCoursesService.getAllLectures(), Messages.LIST_IS_EMPTY);
	}

	@Override
	public IResponse cloneCourse(IRequest idCourse) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		try {
			RequestExtractor requestExtractor = new RequestExtractor(idCourse);
			mCoursesService.cloneCourseById(requestExtractor.extractIdCourse());
			return responseBuilder.build(Messages.CLONED_SUCCESSFULLY);
		} catch (CloneNotSupportedException | EntityNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
		return responseBuilder.build(Messages.CLONED_BAD);
	}

	@Override
	public IResponse exportCourse(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mCoursesService.exportCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse exportLector(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mLectorsService.exportCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse exportStudent(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mStudentsService.exportCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse exportTimeTable(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mTimeTableService.exportCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse importCourse(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mCoursesService.importCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse importLector(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mLectorsService.importCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse importStudent(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mStudentsService.importCSV(path);
		return responseBuilder.build(Messages.OK);
	}

	@Override
	public IResponse importTimeTable(IRequest request) {
		ResponseBuilder responseBuilder = new ResponseBuilder();
		RequestExtractor extractor = new RequestExtractor(request);
		String path = extractor.extractPath();
		mTimeTableService.importCSV(path);
		return responseBuilder.build(Messages.OK);
	}

}
