package com.senla.rakickaya.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.controller.api.repositories.ICoursesRepository;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.utils.ListWorker;

public class CoursesRepository implements ICoursesRepository {
	private List<Course> mCourses;

	public CoursesRepository() {
		super();
		mCourses = new ArrayList<>();
	}

	public CoursesRepository(List<Course> mCourses) {
		super();
		this.mCourses = mCourses;
	}

	@Override
	public void addCourse(Course pCourse) {
		mCourses.add(pCourse);

	}

	// null
	@Override
	public Course removeCourse(long pId) {
		return (Course) ListWorker.removeItemById(mCourses, pId);
	}

	@Override
	public void updateCourse(Course pCourse) {
		ListWorker.updateItem(mCourses, pCourse);
	}

	// null
	@Override
	public Course getCourse(long pId) {
		return (Course) ListWorker.getItemById(mCourses, pId);
	}

	@Override
	public List<Course> getListCourses() {
		return mCourses;
	}

}
