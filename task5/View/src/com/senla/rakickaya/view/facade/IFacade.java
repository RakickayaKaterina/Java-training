package com.senla.rakickaya.view.facade;

import java.util.Date;

import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.beans.Lecture;
import com.senla.rakickaya.model.beans.Student;
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
	public Response getDetailedDescription(long pIdCourse);

	// 4
	public Response getSortedTimeTableByDate();

	public Response getSortedTimeTableByAlphabet();

	// 5
	public Response getSortedCoursesByStartDate(Date afterDate);

	public Response getSortedCoursesByCountStudents(Date afterDate);

	public Response getSortedCoursesByLectorName(Date afterDate);

	public Response getSortedCoursesByAlphabet(Date afterDate);

	// 6
	public Response getSortedCurrentCoursesByStartDate(Date currentDate);

	public Response getSortedCurrentCoursesByCountStudents(Date currentDate);

	public Response getSortedCurrentCoursesByLectorName(Date currentDate);

	public Response getSortedCurrentCoursesByAlphabet(Date currentDate);

	// 7
	public Response getTotalCountCourse();

	public Response getTotalCountStudents();

	public Response getTotalCountLectors();
	//8
	public Response getListLessonByDate(Request date);
	// 9
	public Response getPastCourses(Date startDate, Date endDate);
	// 10
	public Response addCourse(Request course);

	// 11
	public Response removeCourse(Request idCourse);

	// 12
	public Response addLectureToCourse(Lecture lecture, long pIdCourse);

	public Response removeLectureFromCourse(long idLecture, long idCourse);

	// 13
	public Response addStudent(Request student);

	public Response addStudentToCourse(long idStudent, long idCourse);

	public Response removeStudent(Request idStudent);

	public Response removeStudentFromCourse(long idStudent, long idCourse);

	// 14
	public Response addLector(Request lector);

	public Response addLectorToCourse(long idLector, long idCourse);

	public Response removeLector(Request idLector);

	public Response removeLectorFromCourse(long idLector, long idCourse);

	// 15
	public Response createTimeTableForLecture(long idLecture, Date dateForLecture);
	

	public Response removeTimeTableForLecture(Request idLecture);
	
}
