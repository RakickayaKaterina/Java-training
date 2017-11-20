package com.senla.rakickaya.courseplanner.api.facade;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;

public interface IFacade {
	public IResponse getSortedCoursesByStartDate();

	public IResponse getSortedCoursesByCountStudents();

	public IResponse getSortedCoursesByLectorName();

	public IResponse getSortedCoursesByAlphabet();

	public IResponse getSortedLectorsByAlphabet();

	public IResponse getSortedLectorsByCountCourses();

	public IResponse getDetailedDescription(IRequest pIdCourse);

	public IResponse getSortedTimeTableByDate();

	public IResponse getSortedTimeTableByAlphabet();

	public IResponse getSortedCoursesByStartDate(IRequest afterDate);

	public IResponse getSortedCoursesByCountStudents(IRequest afterDate);

	public IResponse getSortedCoursesByLectorName(IRequest afterDate);

	public IResponse getSortedCoursesByAlphabet(IRequest afterDate);

	public IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate);

	public IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate);

	public IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate);

	public IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate);

	public IResponse getTotalCountCourse();

	public IResponse getTotalCountStudents();

	public IResponse getTotalCountLectors();

	public IResponse getListLessonByDate(IRequest date);

	public IResponse getPastCourses(IRequest interval);

	public IResponse addCourse(IRequest course);

	public IResponse removeCourse(IRequest idCourse);

	public IResponse addLectureToCourse(IRequest request);

	public IResponse removeLectureFromCourse(IRequest request);

	public IResponse addStudent(IRequest student);

	public IResponse addStudentToCourse(IRequest request);

	public IResponse removeStudent(IRequest idStudent);

	public IResponse removeStudentFromCourse(IRequest request);

	public IResponse addLector(IRequest lector);

	public IResponse addLectorToCourse(IRequest request);

	public IResponse removeLector(IRequest idLector);

	public IResponse removeLectorFromCourse(IRequest request);

	public IResponse createTimeTableForLecture(IRequest request);

	public IResponse removeTimeTableForLecture(IRequest idLecture);

	public IResponse getAllStudents();

	public IResponse getAllLectors();

	public IResponse getAllCourses();

	IResponse getAllLectures();

	IResponse getLecturesByCourse(int n);

	public void save();

	IResponse cloneCourse(IRequest idCourse);
}
