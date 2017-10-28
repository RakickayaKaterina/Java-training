package repositories;

import models.Course;

public interface IRepositoryCourses {
	public void addCourse(Course pCourse);

	public Course removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public Course[] getListCourse();

	public void saveState();

}
