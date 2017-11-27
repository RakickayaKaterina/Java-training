package com.senla.rakickaya.courseplanner.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class CoursesRepository implements ICoursesRepository {
	private static CoursesRepository coursesRepository;

	private List<ICourse> mCourses;

	private CoursesRepository(List<ICourse> mCourses) {
		super();
		this.mCourses = mCourses;
	}

	public static CoursesRepository getInstance() {
		if (coursesRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			coursesRepository = new CoursesRepository(fillerRepositories.getCourses());
		}
		return coursesRepository;
	}

	@Override
	public boolean addCourse(ICourse pCourse) {
		if (getCourse(pCourse.getId()) == null) {
			mCourses.add(pCourse);
			save();
			return true;
		}
		return false;

	}

	@Override
	public ICourse removeCourse(long pId) {
		ICourse course = ListWorker.removeItemById(mCourses, pId);
		if(course!=null){
			save();
		}
		return course;
	}

	@Override
	public void updateCourse(ICourse pCourse) {
		ListWorker.updateItem(mCourses, pCourse);
		save();
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
	public List<ILecture> getAllLectures() {
		List<ICourse> courses = getListCourses();
		List<ILecture> resultList = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			resultList.addAll(courses.get(i).getLectures());
		}
		return resultList;
	}

	@Override
	public void save() {
		try {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			fillerRepositories.writeCourseToFile(mCourses);
		} catch (IOException e) {
			// TODO LOGGER
			e.printStackTrace();
		}

	}

}
