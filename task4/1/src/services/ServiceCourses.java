package services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import models.Course;
import models.Lector;
import repositories.IRepositoryCourses;
import utils.ArrayWorker;
import utils.DateWorker;

public class ServiceCourses implements IServiceCourses {
	IRepositoryCourses mRepositoryCourses;

	public ServiceCourses(IRepositoryCourses mRepositoryCourses) {
		super();
		this.mRepositoryCourses = mRepositoryCourses;
	}

	@Override
	public void addCourse(Course pCourse) {
		mRepositoryCourses.addCourse(pCourse);

	}

	@Override
	public void removeCourse(long pId) {
		// TODO removed

	}

	@Override
	public void updateCourse(Course pCourse) {
		mRepositoryCourses.updateCourse(pCourse);

	}

	@Override
	public Course getCourse(long pId) {
		return mRepositoryCourses.getCourse(pId);
	}

	@Override
	public void saveState() {
		// TODO save

	}

	@Override
	public void removeLectorFromCourses(Lector pLector) {
		for (Course course : mRepositoryCourses.getListCourse()) {
			if (course != null && course.getLector().getId() == pLector.getId()) {
				course.setLector(null);
			}

		}

	}
	
	@Override
	public Course[] sort(Comparator<Course> mComparator) {
		Course[] listCourses = mRepositoryCourses.getListCourse();
		Arrays.sort(listCourses, mComparator);
		return listCourses;
	}

	@Override
	public Course[] getListCourses() {
		return mRepositoryCourses.getListCourse();
	}

	@Override
	public Course[] getSortedListCoursesAfterDate(Date pDate, Comparator<Course> pComparator) {
		Course[] resultList = new Course[10];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (DateWorker.isAfterDate(pDate, courses[i].getStartDate())) {
				ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		Arrays.sort(resultList,pComparator);
		return resultList;
	}

	@Override
	public Course[] getSortedListCurrentCourses(Date pCurrentDate,Comparator<Course> pComparator) {
		Course[] resultList = new Course[10];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (DateWorker.isBetweenDate(pCurrentDate, courses[i].getStartDate(), courses[i].getEndDate())) {
				ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		Arrays.sort(resultList,pComparator);
		return resultList;
	}
}