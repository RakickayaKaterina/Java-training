package com.senla.rakickaya.controller.services;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.controller.api.repositories.ICoursesRepository;
import com.senla.rakickaya.controller.api.repositories.ILectorsRepository;
import com.senla.rakickaya.controller.api.services.ILectorsService;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.exception.EntityNotFoundException;

public class LectorsService implements ILectorsService {
	private final ILectorsRepository mRepositoryLectors;
	private final ICoursesRepository mRepositoryCourses;

	public LectorsService(ILectorsRepository mRepositoryLectors, ICoursesRepository mRepositoryCourses) {
		super();
		this.mRepositoryLectors = mRepositoryLectors;
		this.mRepositoryCourses = mRepositoryCourses;
	}

	@Override
	public void addLector(Lector pLector) {
		mRepositoryLectors.addLector(pLector);
	}

	@Override
	public void removeLector(long pId) throws EntityNotFoundException {
		Lector lector = mRepositoryLectors.removeLector(pId);
		if(lector == null){
			throw new EntityNotFoundException();
		}
		for (Course course : mRepositoryCourses.getListCourses()) {
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
	public List<Lector> getListLectors() {
		return mRepositoryLectors.getListLectors();
	}

	@Override
	public int getCountCoursesByLector(long idLector) {
		int count = 0;
		List<Course> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getLector() != null && courses.get(i).getLector().getId() == idLector) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<Lector> getSortedList(Comparator<Lector> comparator) {
		List<Lector> listLectors = mRepositoryLectors.getListLectors();
		listLectors.sort(comparator);
		return listLectors;
	}

	@Override
	public Map<Lector, Integer> getLectorsInformation() {
		//TODO 
		Map<Lector, Integer> map = new LinkedHashMap<>();
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
		for (Lector lector : getSortedList(comparator)) {
			map.put(lector, getCountCoursesByLector(lector.getId()));
			//	result.append(lector.toString() + " count courses: " + getCountCoursesByLector(lector.getId()) + "\n");
			
		}
		return map;
	}

	@Override
	public int getTotalCountLectors() {
		return mRepositoryLectors.getListLectors().size();
	}

}
