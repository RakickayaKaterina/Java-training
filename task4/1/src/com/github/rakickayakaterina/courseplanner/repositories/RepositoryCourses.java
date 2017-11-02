package com.github.rakickayakaterina.courseplanner.repositories;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;

public class RepositoryCourses implements IRepositoryCourses {
	private Course[] mCourses;

	public RepositoryCourses(int countCourses) {
		super();
		mCourses = new Course[countCourses];
	}

	public RepositoryCourses(Course[] mCourses) {
		super();
		this.mCourses = mCourses;
	}

	@Override
	public void addCourse(Course pCourse) {
		mCourses = (Course[]) ArrayWorker.addToArray(pCourse, mCourses);

	}

	@Override
	public Course removeCourse(long pId) {
		return (Course) ArrayWorker.removeFromArray(pId, mCourses);
	}

	@Override
	public void updateCourse(Course pCourse) {
		ArrayWorker.updatePosition(pCourse, mCourses);

	}

	@Override
	public Course getCourse(long pId) {
		int position = ArrayWorker.getPositionById(pId, mCourses);
		if (position >= 0) {
			return mCourses[position];
		}
		return null;
	}

	@Override
	public Course[] getListCourse() {
		return mCourses;
	}

}
