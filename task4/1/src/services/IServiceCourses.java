package services;

import java.util.Comparator;
import java.util.Date;

import models.Course;
import models.Lector;

public interface IServiceCourses {
	public void addCourse(Course pCourse);

	public void removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public Course[] getListCourses();

	public Course[] sort(Comparator<Course> mComparator);

	public void saveState();

	public Course[] getSortedListCoursesAfterDate(Date pDate, Comparator<Course> pComparator);

	public Course[] getSortedListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator);

	public void removeLectorFromCourses(Lector pLector);
}
