package com.senla.rakickaya.controller.api.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.exception.EntityNotFoundException;


public interface ILectorsService {
	public void addLector(Lector pLector);

	public void removeLector(long pId) throws EntityNotFoundException;

	public void updateLector(Lector pLector);

	public Lector getLector(long pId);

	public List<Lector> getListLectors();

	public int getCountCoursesByLector(long pIdLector);

	public List<Lector> getSortedList(Comparator<Lector> pComparator);

	public Map<Lector,Integer> getLectorsInformation();

	public int getTotalCountLectors();

}
