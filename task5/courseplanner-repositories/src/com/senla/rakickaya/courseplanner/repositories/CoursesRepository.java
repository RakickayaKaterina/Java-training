package com.senla.rakickaya.courseplanner.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.utils.ListWorker;

public class CoursesRepository implements ICoursesRepository {
	private static CoursesRepository coursesRepository;

	public static CoursesRepository getInstance() {
		if (coursesRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			coursesRepository = new CoursesRepository(fillerRepositories.getCourses());
		}
		return coursesRepository;
	}

	private List<ICourse> mCourses;

	private CoursesRepository(List<ICourse> mCourses) {
		super();
		this.mCourses = mCourses;
	}

	@Override
	public void addCourse(ICourse pCourse) {
		mCourses.add(pCourse);

	}

	@Override
	public ICourse removeCourse(long pId) {
		return ListWorker.removeItemById(mCourses, pId);
	}

	@Override
	public void updateCourse(ICourse pCourse) {
		ListWorker.updateItem(mCourses, pCourse);
	}

	@Override
	public ICourse getCourse(long pId) {
		return (ICourse) ListWorker.getItemById(mCourses, pId);
	}

	@Override
	public List<ICourse> getListCourses() {
		return mCourses;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeCourseToFile(mCourses);
		
	}

}
