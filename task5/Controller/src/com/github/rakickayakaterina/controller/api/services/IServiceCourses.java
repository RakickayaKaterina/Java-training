package com.github.rakickayakaterina.controller.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.github.rakickayakaterina.model.beans.Course;
import com.github.rakickayakaterina.model.beans.Lecture;

public interface IServiceCourses {
	public void addCourse(Course pCourse);

	public void removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public List<Course> getListCourses();

	public void addStudentToCourse(long pIdStudent, long pIdCourse);

	public void removeStudentFromCourse(long pIdStudent, long pIdCourse);

	public void addLectorToCourse(long pIdLector, long pIdCourse);

	public void removeLectorFromCourse(long pIdLector, long pIdCourse);

	public void addLectureToCourse(Lecture pLecture, long pIdCourse);

	public void removeLectureFromCourse(long pIdLecture, long pIdCourse);

	public List<Course> getSortedList(Comparator<Course> pComparator);

	public List<Course> getListCoursesAfterDate(Date pDate, Comparator<Course> pComparator);

	public List<Course> getListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator);

	public int getTotalCountCourses();

	public List<Course> getPastCourses(Date startDateSub, Date endDateSub);

}
