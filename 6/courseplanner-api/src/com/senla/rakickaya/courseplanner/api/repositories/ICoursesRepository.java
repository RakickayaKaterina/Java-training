package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;

public interface ICoursesRepository {
	public boolean addCourse(ICourse pCourse);

	public ICourse removeCourse(long pId);

	public void updateCourse(ICourse pCourse);

	public ICourse getCourse(long pId);

	public List<ICourse> getListCourses();

	public void save();

	List<ILecture> getAllLectures();

}
