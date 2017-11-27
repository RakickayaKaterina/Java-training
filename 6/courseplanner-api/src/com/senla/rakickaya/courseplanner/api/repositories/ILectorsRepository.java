package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILector;


public interface ILectorsRepository {
	public boolean addLector(ILector pLector);

	public ILector removeLector(long pId);

	public void updateLector(ILector pLector);

	public ILector getLector(long pId);

	public List<ILector> getListLectors();

	public void save();

}
