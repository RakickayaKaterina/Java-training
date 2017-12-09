package com.senla.rakickaya.courseplanner.api.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ILectorsService {
	void addLector(ILector pLector);

	void removeLector(long pId) throws EntityNotFoundException;

	void updateLector(ILector pLector);

	ILector getLector(long pId);

	List<ILector> getLectors();

	int getCountCoursesByLector(long pIdLector);

	List<ILector> getSortedList(Comparator<ILector> pComparator);

	Map<ILector, Integer> getLectorsInformation();

	int getTotalCountLectors();

	void exportCSV(String path);

	void importCSV(String path);

}
