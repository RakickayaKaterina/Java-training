package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;

public interface ICoursesRepository {
	boolean addCourse(ICourse pCourse);

	ICourse removeCourse(long pId);

	void updateCourse(ICourse pCourse);

	ICourse getCourse(long pId);

	List<ICourse> getCourses();

	void save();

	List<ILecture> getAllLectures();

}
