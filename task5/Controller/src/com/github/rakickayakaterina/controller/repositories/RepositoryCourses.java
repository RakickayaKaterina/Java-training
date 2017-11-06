package com.github.rakickayakaterina.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.model.beans.Course;
import com.github.rakickayakaterina.utils.ListWorker;

public class RepositoryCourses implements IRepositoryCourses {
	private List<Course> mCourses;

	public RepositoryCourses() {
		super();
		mCourses = new ArrayList<>();
	}

	public RepositoryCourses(List<Course> mCourses) {
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
