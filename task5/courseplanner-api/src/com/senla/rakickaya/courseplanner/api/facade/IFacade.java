package com.senla.rakickaya.courseplanner.api.facade;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;

public interface IFacade {
	public IResponse getSortedCoursesByStartDate();

	public IResponse getSortedCoursesByCountStudents();

	public IResponse getSortedCoursesByLectorName();

	public IResponse getSortedCoursesByAlphabet();

	// 2
	public IResponse getSortedLectorsByAlphabet();

	public IResponse getSortedLectorsByCountCourses();

	// 3
	public IResponse getDetailedDescription(IRequest pIdCourse);// id

	// 4
	public IResponse getSortedTimeTableByDate();

	public IResponse getSortedTimeTableByAlphabet();

	// 5
	public IResponse getSortedCoursesByStartDate(IRequest afterDate);// Date

	public IResponse getSortedCoursesByCountStudents(IRequest afterDate);// Date

	public IResponse getSortedCoursesByLectorName(IRequest afterDate);

	public IResponse getSortedCoursesByAlphabet(IRequest afterDate);

	// 6
	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate);

	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate);

	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate);

	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate);

	// 7
	public IResponse getTotalCountCourse();

	public IResponse getTotalCountStudents();

	public IResponse getTotalCountLectors();

	// 8
	public IResponse getListLessonByDate(IRequest date);

	// 9
	public IResponse getPastCourses(IRequest interval);// Date,Date
	// 10

	public IResponse addCourse(IRequest course);

	// 11
	public IResponse removeCourse(IRequest idCourse);

	// 12
	public IResponse addLectureToCourse(IRequest request);// Lecture, idCourse

	public IResponse removeLectureFromCourse(IRequest request);// idLecture,
																// idCourse

	// 13
	public IResponse addStudent(IRequest student);

	public IResponse addStudentToCourse(IRequest request);// idStudent, idCourse

	public IResponse removeStudent(IRequest idStudent);

	public IResponse removeStudentFromCourse(IRequest request);// idStudent,idCourse

	// 14
	public IResponse addLector(IRequest lector);

	public IResponse addLectorToCourse(IRequest request);// idLector, idCourse

	public IResponse removeLector(IRequest idLector);

	public IResponse removeLectorFromCourse(IRequest request);// idLector,idCourse

	// 15
	public IResponse createTimeTableForLecture(IRequest request);// idLecture,Date
																	// dateForLecture

	public IResponse removeTimeTableForLecture(IRequest idLecture);

	public IResponse getAllStudents();

	public IResponse getAllLectors();

	public IResponse getAllCourses();

	IResponse getAllLectures();

	IResponse getLecturesByCourse(int n);

	public void save();
}
