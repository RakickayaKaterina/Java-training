package com.github.rakickayakaterina.courseplanner.api.repositories;

import com.github.rakickayakaterina.courseplanner.beans.Course;

public interface IRepositoryCourses {
	public void addCourse(Course pCourse);

	public Course removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public Course[] getListCourse();

}
