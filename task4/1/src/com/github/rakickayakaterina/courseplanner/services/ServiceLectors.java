package com.github.rakickayakaterina.courseplanner.services;

import java.util.Arrays;
import java.util.Comparator;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryLectors;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceLectors;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lector;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;

public class ServiceLectors implements IServiceLectors {
	private final IRepositoryLectors mRepositoryLectors;
	private final IRepositoryCourses mRepositoryCourses;

	public ServiceLectors(IRepositoryLectors mRepositoryLectors, IRepositoryCourses mRepositoryCourses) {
		super();
		this.mRepositoryLectors = mRepositoryLectors;
		this.mRepositoryCourses = mRepositoryCourses;
	}

	@Override
	public void addLector(Lector pLector) {
		mRepositoryLectors.addLector(pLector);
	}

	@Override
	public void removeLector(long pId) {
		mRepositoryLectors.removeLector(pId);
		for (Course course : mRepositoryCourses.getListCourse()) {
			if (course != null && course.getLector() != null && course.getLector().getId() == pId) {
				course.setLector(null);
			}

		}
	}

	@Override
	public void updateLector(Lector pLector) {
		mRepositoryLectors.updateLector(pLector);

	}

	@Override
	public Lector getLector(long id) {
		return mRepositoryLectors.getLector(id);
	}

	@Override
	public Lector[] getListLectors() {
		return mRepositoryLectors.getListLectors();
	}

	@Override
	public int getCountCoursesByLector(long idLector) {
		int count = 0;
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < courses.length; i++) {
			if (courses[i] != null && courses[i].getLector() != null && courses[i].getLector().getId() == idLector) {
				count++;
			}

		}

		return count;
	}

	@Override
	public Lector[] getSortedList(Comparator<Lector> comparator) {
		Lector[] listLectors = mRepositoryLectors.getListLectors();
		Arrays.sort(listLectors, comparator);
		return listLectors;
	}

	@Override
	public String getLectorsInformation() {
		Comparator<Lector> comparator = new Comparator<Lector>() {

			@Override
			public int compare(Lector o1, Lector o2) {
				if (o1 != null && o2 != null) {
					Integer count = getCountCoursesByLector(o1.getId());
					return count.compareTo(getCountCoursesByLector(o2.getId()));
				} else if (o1 != null && o2 == null) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		StringBuilder result = new StringBuilder();
		for (Lector lector : getSortedList(comparator)) {
			if (lector != null) {
				result.append(lector.toString() + " count courses: " + getCountCoursesByLector(lector.getId()) + "\n");
			}
		}
		return result.toString();
	}

	@Override
	public int getTotalCountLectors() {
		return ArrayWorker.getLenghtArray(mRepositoryLectors.getListLectors());
	}

}
