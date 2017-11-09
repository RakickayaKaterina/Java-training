package com.senla.rakickaya.view.api.facade;

import com.senla.rakickaya.view.dataExchange.Request;
import com.senla.rakickaya.view.dataExchange.Response;


public interface IFacade {
	public Response getSortedCoursesByStartDate();

	public Response getSortedCoursesByCountStudents();

	public Response getSortedCoursesByLectorName();

	public Response getSortedCoursesByAlphabet();

	// 2
	public Response getSortedLectorsByAlphabet();

	public Response getSortedLectorsByCountCourses();

	// 3
	public Response getDetailedDescription(Request pIdCourse);//id

	// 4
	public Response getSortedTimeTableByDate();

	public Response getSortedTimeTableByAlphabet();

	// 5
	public Response getSortedCoursesByStartDate(Request afterDate);//Date

	public Response getSortedCoursesByCountStudents(Request afterDate);//Date

	public Response getSortedCoursesByLectorName(Request afterDate);

	public Response getSortedCoursesByAlphabet(Request afterDate);

	// 6
	public Response getSortedCurrentCoursesByStartDate(Request currentDate);

	public Response getSortedCurrentCoursesByCountStudents(Request currentDate);

	public Response getSortedCurrentCoursesByLectorName(Request currentDate);

	public Response getSortedCurrentCoursesByAlphabet(Request currentDate);

	// 7
	public Response getTotalCountCourse();

	public Response getTotalCountStudents();

	public Response getTotalCountLectors();
	//8
	public Response getListLessonByDate(Request date);
	// 9
	public Response getPastCourses(Request interval);//Date,Date
	// 10
	public Response addCourse(Request course);

	// 11
	public Response removeCourse(Request idCourse);

	// 12
	public Response addLectureToCourse(Request request);//Lecture, idCourse

	public Response removeLectureFromCourse(Request request);//idLecture, idCourse

	// 13
	public Response addStudent(Request student);

	public Response addStudentToCourse(Request request);//idStudent, idCourse

	public Response removeStudent(Request idStudent);

	public Response removeStudentFromCourse(Request request);//idStudent,idCourse

	// 14
	public Response addLector(Request lector);

	public Response addLectorToCourse(Request request);//idLector, idCourse

	public Response removeLector(Request idLector);

	public Response removeLectorFromCourse(Request request);//idLector,idCourse

	// 15
	public Response createTimeTableForLecture(Request request);//idLecture,Date dateForLecture
	

	public Response removeTimeTableForLecture(Request idLecture);
	
}
