package com.github.rakickayakaterina.courseplanner.api.services;

import java.util.Comparator;

import com.github.rakickayakaterina.courseplanner.beans.Lector;

public interface IServiceLectors {
	public void addLector(Lector pLector);

	public void removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long id);

	public Lector[] getListLectors();

	public int getCountCoursesByLector(long idLector);

	public Lector[] getSortedList(Comparator<Lector> comparator);

	public String getLectorsInformation();

	public int getTotalCountLectors();

}
