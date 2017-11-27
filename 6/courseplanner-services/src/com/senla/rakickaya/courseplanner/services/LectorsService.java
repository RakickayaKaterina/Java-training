package com.senla.rakickaya.courseplanner.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.ILectorsRepository;
import com.senla.rakickaya.courseplanner.api.services.ILectorsService;
import com.senla.rakickaya.courseplanner.csv.CSVConverter;
import com.senla.rakickaya.courseplanner.csv.CSVObject;
import com.senla.rakickaya.courseplanner.csv.CSVWorker;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.LectorsRepository;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;

public class LectorsService implements ILectorsService {
	private final ILectorsRepository mRepositoryLectors;
	private final ICoursesRepository mRepositoryCourses;

	public LectorsService() {
		super();
		this.mRepositoryLectors = LectorsRepository.getInstance();
		this.mRepositoryCourses = CoursesRepository.getInstance();
	}

	@Override
	public void addLector(ILector pLector) {
		mRepositoryLectors.addLector(pLector);
	}

	@Override
	public void removeLector(long pId) throws EntityNotFoundException {
		ILector lector = mRepositoryLectors.removeLector(pId);
		if (lector == null) {
			throw new EntityNotFoundException();
		}
		for (ICourse course : mRepositoryCourses.getCourses()) {
			if (course != null && course.getLector() != null && course.getLector().getId() == pId) {
				course.setLector(null);
			}
		}
	}

	@Override
	public void updateLector(ILector pLector) {
		mRepositoryLectors.updateLector(pLector);

	}

	@Override
	public ILector getLector(long id) {
		return mRepositoryLectors.getLector(id);
	}

	@Override
	public List<ILector> getLectors() {
		return mRepositoryLectors.getLectors();
	}

	@Override
	public int getCountCoursesByLector(long idLector) {
		int count = 0;
		List<ICourse> courses = mRepositoryCourses.getCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getLector() != null && courses.get(i).getLector().getId() == idLector) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<ILector> getSortedList(Comparator<ILector> comparator) {
		List<ILector> listLectors = mRepositoryLectors.getLectors();
		listLectors.sort(comparator);
		return listLectors;
	}

	@Override
	public Map<ILector, Integer> getLectorsInformation() {
		// TODO
		Map<ILector, Integer> map = new LinkedHashMap<>();
		Comparator<ILector> comparator = new Comparator<ILector>() {

			@Override
			public int compare(ILector o1, ILector o2) {
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
		for (ILector lector : getSortedList(comparator)) {
			map.put(lector, getCountCoursesByLector(lector.getId()));

		}
		return map;
	}
	@Override
	public void exportCSV(String path) {
		CSVWorker worker = new CSVWorker(path);
		List<ILector> lectors = mRepositoryLectors.getLectors();
		List<CSVObject> objects = new ArrayList<CSVObject>();
		for (ILector lector : lectors) {
			objects.add(CSVObject.valueOf(lector));
		}
		worker.writeCSV(objects);
	}

	@Override
	public void importCSV(String path) {
		CSVWorker worker = new CSVWorker(path);
		List<CSVObject> objects = worker.readCSV();
		List<ILector> lectors = new ArrayList<>();
		for (CSVObject obj : objects) {
			lectors.add(CSVConverter.parseLector(obj));
		}
		for (ILector lector : lectors) {
			if (!mRepositoryLectors.addLector(lector)) {
				mRepositoryLectors.updateLector(lector);
			}
			else{
				GeneratorId generatorId = GeneratorId.getInstance();
				long id = generatorId.getIdLector();
				if(lector.getId() > id){
					generatorId.setIdLector(id);
				}
			}
		}
	}
	@Override
	public int getTotalCountLectors() {
		return mRepositoryLectors.getLectors().size();
	}
}
