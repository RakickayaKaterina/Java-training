package com.senla.rakickaya.controller.api.repositories;

import java.util.List;

import com.senla.rakickaya.model.beans.Course;

public interface ICoursesRepository {
	public void addCourse(Course pCourse);

	public Course removeCourse(long pId);

	public void updateCourse(Course pCourse);

	public Course getCourse(long pId);

	public List<Course> getListCourses();

}
