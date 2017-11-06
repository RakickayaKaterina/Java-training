package com.github.rakickayakaterina.controller.api.repositories;

import java.util.List;

import com.github.rakickayakaterina.model.beans.Course;

public interface IRepositoryCourses {
	public void addCourse(Course pCourse);

	public Course removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public List<Course> getListCourses();

}
