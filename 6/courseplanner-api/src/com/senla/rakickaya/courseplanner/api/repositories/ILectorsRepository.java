package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILector;


public interface ILectorsRepository {
	boolean addLector(ILector pLector);

	ILector removeLector(long pId);

	void updateLector(ILector pLector);

	ILector getLector(long pId);

	List<ILector> getLectors();

	void save();

}
