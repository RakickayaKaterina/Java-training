package com.senla.rakickaya.courseplanner.api.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ILectorsService {
	public void addLector(ILector pLector);

	public void removeLector(long pId) throws EntityNotFoundException;

	public void updateLector(ILector pLector);

	public ILector getLector(long pId);

	public List<ILector> getListLectors();

	public int getCountCoursesByLector(long pIdLector);

	public List<ILector> getSortedList(Comparator<ILector> pComparator);

	public Map<ILector, Integer> getLectorsInformation();

	public int getTotalCountLectors();

}
