package com.senla.rakickaya.courseplanner.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.Intentions;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.dataExchange.RequestBuilder;
import com.senla.rakickaya.courseplanner.utils.Decoder;

public class FacadeClient implements IFacade {
	private static final Logger logger = Logger.getLogger(FacadeClient.class.getName());
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Decoder decoder;

	public FacadeClient() {
		super();
		init();

	}

	private void init() {
		try {
			socket = new Socket("localhost", 8888);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			decoder = new Decoder();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private IResponse getResponse(IRequest req) {
		IResponse res = null;
		try {
			String request = decoder.encode(req);
			writer.write(request);
			writer.newLine();
			writer.flush();
			String response = reader.readLine();
			res = decoder.decode(response);

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return res;
	}

	@Override
	protected void finalize() throws Throwable {
		socket.close();
		reader.close();
		writer.close();
		super.finalize();
	}

	@Override
	public IResponse getSortedCoursesByStartDate() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_COURSES_BY_START_DATE);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedCoursesByCountStudents() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_COURSES_BY_COUNT_STUDENT);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedCoursesByLectorName() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_COURSES_BY_LECTOR_NAME);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedCoursesByAlphabet() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_COURSES_BY_ALPH);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedLectorsByAlphabet() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_LECTORS_BY_ALPH);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedLectorsByCountCourses() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_LECTORS_BY_COUNT_STUDENT);
		return getResponse(request);
	}

	@Override
	public IResponse getDetailedDescription(IRequest pIdCourse) {
		pIdCourse.setIntent(Intentions.GET_DETAILED_DESCRIPTION);
		return getResponse(pIdCourse);
	}

	@Override
	public IResponse getSortedTimeTableByDate() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_TIME_TABLE_BY_DATE);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedTimeTableByAlphabet() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_SORTED_TIME_TABLE_BY_ALPH);
		return getResponse(request);
	}

	@Override
	public IResponse getSortedCoursesByStartDate(IRequest afterDate) {
		afterDate.setIntent(Intentions.GET_SORTED_COURSES_BY_START_DATE_PARAM);
		return getResponse(afterDate);
	}

	@Override
	public IResponse getSortedCoursesByCountStudents(IRequest afterDate) {
		afterDate.setIntent(Intentions.GET_SORTED_COURSES_BY_COUNT_STUDENT_PARAM);
		return getResponse(afterDate);
	}

	@Override
	public IResponse getSortedCoursesByLectorName(IRequest afterDate) {
		afterDate.setIntent(Intentions.GET_SORTED_COURSES_BY_LECTOR_NAME_PARAM);
		return getResponse(afterDate);
	}

	@Override
	public IResponse getSortedCoursesByAlphabet(IRequest afterDate) {
		afterDate.setIntent(Intentions.GET_SORTED_COURSES_BY_ALPH_PARAM);
		return getResponse(afterDate);
	}

	@Override
	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate) {
		currentDate.setIntent(Intentions.GET_SORTED_CURRENT_COURSES_BY_START_DATE);
		return getResponse(currentDate);
	}

	@Override
	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate) {
		currentDate.setIntent(Intentions.GET_SORTED_CURRENT_COURSES_BY_COUNT_STUDENT);
		return getResponse(currentDate);
	}

	@Override
	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate) {
		currentDate.setIntent(Intentions.GET_SORTED_CURRENT_COURSES_BY_LECTOR_NAME);
		return getResponse(currentDate);
	}

	@Override
	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate) {
		currentDate.setIntent(Intentions.GET_SORTED_CURRENT_COURSES_BY_ALPH);
		return getResponse(currentDate);
	}

	@Override
	public IResponse getTotalCountCourse() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_TOTAL_COUNT_COURSES);
		return getResponse(request);
	}

	@Override
	public IResponse getTotalCountStudents() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_TOTAL_COUNT_STUDENTS);
		return getResponse(request);
	}

	@Override
	public IResponse getTotalCountLectors() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_TOTAL_COUNT_LECTORS);
		return getResponse(request);
	}

	@Override
	public IResponse getLessonsByDate(IRequest date) {
		date.setIntent(Intentions.GET_LESSONS_BY_DATE);
		return getResponse(date);
	}

	@Override
	public IResponse getPastCourses(IRequest interval) {
		interval.setIntent(Intentions.GET_PAST_COURSES);
		return getResponse(interval);
	}

	@Override
	public IResponse addCourse(IRequest course) {
		course.setIntent(Intentions.ADD_COURSE);
		return getResponse(course);
	}

	@Override
	public IResponse removeCourse(IRequest idCourse) {
		idCourse.setIntent(Intentions.REMOVE_COURSE);
		return getResponse(idCourse);
	}

	@Override
	public IResponse addLectureToCourse(IRequest request) {
		request.setIntent(Intentions.ADD_LECTURE_TO_COURSE);
		return getResponse(request);
	}

	@Override
	public IResponse removeLectureFromCourse(IRequest request) {
		request.setIntent(Intentions.REMOVE_LECTURE_FROM_COURSE);
		return getResponse(request);
	}

	@Override
	public IResponse addStudent(IRequest student) {
		student.setIntent(Intentions.ADD_STUDENT);
		return getResponse(student);
	}

	@Override
	public IResponse addStudentToCourse(IRequest request) {
		request.setIntent(Intentions.ADD_STUDENT_TO_COURSE);
		return getResponse(request);
	}

	@Override
	public IResponse removeStudent(IRequest idStudent) {
		idStudent.setIntent(Intentions.REMOVE_STUDENT);
		return getResponse(idStudent);
	}

	@Override
	public IResponse removeStudentFromCourse(IRequest request) {
		request.setIntent(Intentions.REMOVE_STUDENT_FROM_COURSE);
		return getResponse(request);
	}

	@Override
	public IResponse addLector(IRequest lector) {
		lector.setIntent(Intentions.ADD_LECTOR);
		return getResponse(lector);
	}

	@Override
	public IResponse addLectorToCourse(IRequest request) {
		request.setIntent(Intentions.ADD_LECTOR_TO_COURSE);
		return getResponse(request);
	}

	@Override
	public IResponse removeLector(IRequest idLector) {
		idLector.setIntent(Intentions.REMOVE_LECTOR);
		return getResponse(idLector);
	}

	@Override
	public IResponse removeLectorFromCourse(IRequest request) {
		request.setIntent(Intentions.REMOVE_LECTOR_FROM_COURSE);
		return getResponse(request);
	}

	@Override
	public IResponse createTimeTableForLecture(IRequest request) {
		request.setIntent(Intentions.CREATE_TIME_TABLE_FOR_LECTURE);
		return getResponse(request);
	}

	@Override
	public IResponse removeTimeTableForLecture(IRequest idLecture) {
		idLecture.setIntent(Intentions.REMOVE_TIME_TABLE_FOR_LECTURE);
		return getResponse(idLecture);
	}

	@Override
	public IResponse getAllStudents() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_ALL_STUDENTS);
		return getResponse(request);
	}

	@Override
	public IResponse getAllLectors() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_ALL_LECTORS);
		return getResponse(request);
	}

	@Override
	public IResponse getAllCourses() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_ALL_COURSES);
		return getResponse(request);
	}

	@Override
	public IResponse getAllLectures() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_ALL_LECTURES);
		return getResponse(request);
	}

	@Override
	public IResponse cloneCourse(IRequest idCourse) {
		idCourse.setIntent(Intentions.CLONE_COURSES);
		return getResponse(idCourse);
	}

	@Override
	public IResponse exportCourse(IRequest request) {
		request.setIntent(Intentions.EXPORT_COURSES);
		return getResponse(request);
	}

	@Override
	public IResponse importCourse(IRequest request) {
		request.setIntent(Intentions.IMPORT_COURSES);
		return getResponse(request);
	}

	@Override
	public IResponse exportTimeTable(IRequest request) {
		request.setIntent(Intentions.EXPORT_TIME_TABLE);
		return getResponse(request);
	}

	@Override
	public IResponse exportStudent(IRequest request) {
		request.setIntent(Intentions.IMPORT_TIME_TABLE);
		return getResponse(request);
	}

	@Override
	public IResponse exportLector(IRequest request) {
		request.setIntent(Intentions.EXPORT_LECTORS);
		return getResponse(request);
	}

	@Override
	public IResponse importTimeTable(IRequest request) {
		request.setIntent(Intentions.IMPORT_LECTORS);
		return getResponse(request);
	}

	@Override
	public IResponse importStudent(IRequest request) {
		request.setIntent(Intentions.EXPORT_STUDENTS);
		return getResponse(request);
	}

	@Override
	public IResponse importLector(IRequest request) {
		request.setIntent(Intentions.IMPORT_STUDENTS);
		return getResponse(request);
	}

	@Override
	public IResponse getCoursesWithLectures() {
		IRequest request = new RequestBuilder().build();
		request.setIntent(Intentions.GET_ALL_COURSES_WITH_LECTURE);
		return getResponse(request);
	}

}
