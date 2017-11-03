package com.github.rakickayakaterina.courseplanner.api.services;

import java.util.Comparator;
import java.util.Date;

import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lecture;

public interface IServiceCourses {
	public void addCourse(Course pCourse);

	public void removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public Course[] getListCourses();

	public void addStudentToCourse(long pIdStudent, long pIdCourse);

	public void removeStudentFromCourse(long pIdStudent, long pIdCourse);

	public void addLectorToCourse(long pIdLector, long pIdCourse);

	public void removeLectorFromCourse(long pIdLector, long pIdCourse);

	public void addLectureToCourse(Lecture lecture, long pIdCourse);

	public void removeLectureFromCourse(long pIdLecture, long pIdCourse);

	public Course[] getSortedList(Comparator<Course> pComparator);

	public Course[] getListCoursesAfterDate(Date pDate, Comparator<Course> pComparator);

	public Course[] getListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator);

	public int getTotalCountCourses();

	public Course[] getPastCourses(Date startDateSub, Date endDateSub);

}
