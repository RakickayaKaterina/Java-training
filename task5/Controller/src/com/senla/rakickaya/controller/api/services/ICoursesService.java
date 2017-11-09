package com.senla.rakickaya.controller.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lecture;
import com.senla.rakickaya.model.exception.EntityNotFoundException;

public interface ICoursesService {
	public void addCourse(Course pCourse);

	public void removeCourse(long pId) throws EntityNotFoundException;

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public List<Course> getListCourses();

	public void addStudentToCourse(long pIdStudent, long pIdCourse);

	public void removeStudentFromCourse(long pIdStudent, long pIdCourse) throws EntityNotFoundException;

	public void addLectorToCourse(long pIdLector, long pIdCourse);

	public void removeLectorFromCourse(long pIdLector, long pIdCourse) throws EntityNotFoundException;

	public void addLectureToCourse(Lecture pLecture, long pIdCourse);

	public void removeLectureFromCourse(long pIdLecture, long pIdCourse) throws EntityNotFoundException;

	public List<Course> getSortedList(Comparator<Course> pComparator);

	public List<Course> getListCoursesAfterDate(Date pDate, Comparator<Course> pComparator);

	public List<Course> getListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator);

	public int getTotalCountCourses();

	public List<Course> getPastCourses(Date startDateSub, Date endDateSub);

}
