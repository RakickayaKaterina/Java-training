package com.github.rakickayakaterina.controller.api.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.github.rakickayakaterina.model.beans.Lector;


public interface IServiceLectors {
	public void addLector(Lector pLector);

	public void removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long pId);

	public List<Lector> getListLectors();

	public int getCountCoursesByLector(long pIdLector);

	public List<Lector> getSortedList(Comparator<Lector> pComparator);

	public Map<Lector,Integer> getLectorsInformation();

	public int getTotalCountLectors();

}
